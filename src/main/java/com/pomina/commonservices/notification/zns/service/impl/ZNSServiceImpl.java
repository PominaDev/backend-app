package com.pomina.commonservices.notification.zns.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomina.commonservices.notification.zns.config.ZaloZNSConfig;
import com.pomina.commonservices.notification.zns.mapper.ZNSMapper;
import com.pomina.commonservices.notification.zns.model.entity.ZNSEntity;
import com.pomina.commonservices.notification.zns.model.request.ZaloZNSRequest;
import com.pomina.commonservices.notification.zns.model.response.OaAccessTokenResponse;
import com.pomina.commonservices.notification.zns.model.response.ZaloZNSResponse;
import com.pomina.commonservices.notification.zns.service.ZNSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZNSServiceImpl implements ZNSService {

    private final RestTemplate restTemplate;

//    private final ZNSMapper znsMapper;

    private final ZaloZNSConfig zaloZnsConfig;

//    private final ZaloOaTokenService zaloOaTokenService;

    /**
     * Gửi thông báo ZNS đến người dùng.
     * <a href="https://developers.zalo.me/docs/zalo-notification-service/gui-tin-zns/gui-zns-su-dung-development-mode">...</a>
     *
     * @param request {@link ZaloZNSRequest}
     * @return {@link ZaloZNSResponse}
     */
    @Override
    public ZaloZNSResponse sendZaloZNS(ZaloZNSRequest request) {
        return sendZaloZNS(request, false);
    }

    private ZaloZNSResponse sendZaloZNS(ZaloZNSRequest request, boolean retry) {

        HttpHeaders header = buildHeaderZaloZNS();

        Map<String, Object> body = buildRequestBodyZaloZNS(request);

        if (header == null || body.isEmpty()) {
            throw new RuntimeException("Gửi Zalo ZNS thất bại: header/body rỗng");
        }

        ZaloZNSResponse zaloZNSResponse = callApiZaloZNS(header, body);

        if (zaloZNSResponse == null) {
            throw new RuntimeException("Gửi Zalo ZNS thất bại: response null");
        }

        if ((zaloZNSResponse.getError() == -220
                || "access_token is expired or removed".equalsIgnoreCase(zaloZNSResponse.getMessage()))
                && !retry) {
            // Nếu API Zalo response : access_token đã hết hạn hoặc không còn khả dụng.
            // thì renew access token.
            renewAccessToken();
            return sendZaloZNS(request, true);
        } else if (zaloZNSResponse.getData() != null) {
            saveZns(request, zaloZNSResponse);
        }

        return zaloZNSResponse;
    }

    /**
     * Dùng Refresh Token để lấy Access Token.
     * </br>
     * Access Token có hiệu lực 25 giờ.
     * </br>
     * Cron Job: Chạy mỗi 23 giờ một lần.
     * <a href="https://developers.zalo.me/docs/official-account/bat-dau/xac-thuc-va-uy-quyen-cho-ung-dung-new">...</a>
     *
     * @return {@link OaAccessTokenResponse}
     */
    @Override
//    @Scheduled(fixedDelay = 23 * 60 * 60 * 1000)
    public OaAccessTokenResponse renewAccessToken() {

        HttpHeaders header = buildHeaderOaAccessToken();

        MultiValueMap<String, String> formData = buildRequestBodyOaAccessToken();

        if (header == null || formData.isEmpty()) {
            throw new RuntimeException("Refresh Token thất bại");
        }

        OaAccessTokenResponse oaAccessTokenResponse = sendToOaAccessTokenGateway(header, formData);

        if (oaAccessTokenResponse != null
                && oaAccessTokenResponse.getAccessToken() != null
                && oaAccessTokenResponse.getRefreshToken() != null) {
            saveNewAccessTokenAndRefreshToken(oaAccessTokenResponse);
        } else {
            throw new RuntimeException("Refresh Token thất bại");
        }

        log.info(String.valueOf(oaAccessTokenResponse));

        return oaAccessTokenResponse;
    }

    private HttpHeaders buildHeaderZaloZNS() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

//        header.set("access_token", zaloOaTokenService.getAccessToken());

        return header;
    }

    private Map<String, Object> buildRequestBodyZaloZNS(ZaloZNSRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("mode", request.getMode());
        body.put("phone", request.getPhone());
        body.put("template_id", request.getTemplateId());
        body.put("template_data", request.getTemplateData());
        body.put("tracking_id", request.getTrackingId());

        return body;
    }

    private ZaloZNSResponse callApiZaloZNS(HttpHeaders headers, Map<String, Object> body) {
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String url = zaloZnsConfig.getUrlZaloZNS();

        ResponseEntity<ZaloZNSResponse> response = restTemplate.postForEntity(
                url,
                requestEntity,
                ZaloZNSResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    String.format("Failed to send ZNS request. Status: %s, Body: %s",
                            response.getStatusCode(),
                            response.getBody() != null ? response.getBody() : "null")
            );
        }

        return response.getBody();
    }

    private void saveZns(ZaloZNSRequest request, ZaloZNSResponse response) {

        int statusResponse = 0;
        if (Objects.equals(response.getMessage(), "Success")) {
            statusResponse = 1;
        }

        long responseTime = 0L;
        String sentTime = null; //response.getData().getSentTime();
        if (sentTime != null && !sentTime.isEmpty()) {
            responseTime = Long.parseLong(sentTime);
        }

        ZNSEntity znsEntity = ZNSEntity.builder()
                .from("ZALO_ZNS")
                .toPhone(request.getPhone())
                .requestTime(System.currentTimeMillis())
                .responseTime(responseTime)
                .description(response.getMessage())
                .status(statusResponse)
                .trackingId(request.getTrackingId())
                .errorCode(response.getError())
                .build();
//        znsMapper.insert(znsEntity);
    }

    private HttpHeaders buildHeaderOaAccessToken() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("secret_key", zaloZnsConfig.getSecretKeyZaloZNS());

        return header;
    }

    private MultiValueMap<String, String> buildRequestBodyOaAccessToken() {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

//        String refreshToken = zaloOaTokenService.getRefreshToken();
//        formData.add("refresh_token", refreshToken);
//
//        formData.add("app_id", zaloZnsConfig.getAppIdZaloZNS());
//        formData.add("grant_type", "refresh_token");

        return formData;
    }

    private OaAccessTokenResponse sendToOaAccessTokenGateway(HttpHeaders headers, MultiValueMap<String, String> formData) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        String url = zaloZnsConfig.getUrlRefreshToken();

        ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                request,
                String.class
        );

        log.info(String.valueOf(response));

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    String.format("Failed to refresh token. Status: %s, Body: %s",
                            response.getStatusCode(),
                            response.getBody() != null ? response.getBody() : "null")
            );
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            // Nếu có field "error" và khác 0 (OK) => ném exception
            if (root.has("error") && root.get("error").asInt() != 0) {
                throw new RuntimeException(
                        String.format("Zalo API error. Code: %s, Name: %s, Desc: %s",
                                root.path("error").asInt(),
                                root.path("error_name").asText(),
                                root.path("error_description").asText())
                );
            }

            // Không có error => parse sang response object
            return mapper.treeToValue(root, OaAccessTokenResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Failed to parse refresh token response. Body: %s", response.getBody()), e
            );
        }
    }

    /**
     * Lưu access token - refresh token - expires in mới từ API Zalo trả về vào table zalo_oa_token
     *
     * @param response
     */
    private void saveNewAccessTokenAndRefreshToken(OaAccessTokenResponse response) {

        if (response == null || response.getAccessToken() == null || response.getRefreshToken() == null) {
            throw new IllegalArgumentException("Invalid token response from Zalo API");
        }

        OaAccessTokenResponse oaAccessTokenResponse = OaAccessTokenResponse.builder()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .expiresIn(response.getExpiresIn())
                .build();

        //zaloOaTokenService.saveNewToken(oaAccessTokenResponse);
    }
}
