package com.pomina.commonservices.notification.zns.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.logging.LogService;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.notification.zns.config.ZaloZnsConfig;
import com.pomina.commonservices.notification.zns.mapper.ZaloOaTokenMapper;
import com.pomina.commonservices.notification.zns.mapper.ZaloZnsLogMapper;
import com.pomina.commonservices.notification.zns.model.entity.ZaloOaToken;
import com.pomina.commonservices.notification.zns.model.entity.ZaloZnsLog;
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
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZNSServiceImpl implements ZNSService {

    private final RestTemplate restTemplate;

    private final ZaloZnsLogMapper zaloZnsLogMapper;

    private final ZaloZnsConfig zaloZnsConfig;

    private final ZaloOaTokenMapper zaloOaTokenMapper;

    private final LogService logService;

    /**
     * Gửi thông báo ZNS đến người dùng.
     * <a href="https://developers.zalo.me/docs/zalo-notification-service/gui-tin-zns/gui-zns-su-dung-development-mode">...</a>
     * </br>
     * Retry 1 lần
     *
     * @param request {@link ZaloZNSRequest}
     * @return {@link ZaloZNSResponse}
     */
    @Override
    public ZaloZNSResponse sendZaloZNS(ZaloZNSRequest request) {

        ZaloOaToken zaloOaToken = zaloOaTokenMapper.findLatestToken();

        String accessToken = zaloOaToken.getAccessToken();

        return sendZaloZNS(request, accessToken, false);
    }

    private ZaloZNSResponse sendZaloZNS(ZaloZNSRequest request, String accessToken, boolean retry) {

        HttpHeaders header = buildHeaderZaloZNS(accessToken);

        Map<String, Object> body = buildRequestBodyZaloZNS(request);

        ZaloZNSResponse zaloZNSResponse = callApiZaloZNS(header, body);

        if (zaloZNSResponse == null) {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        // Nếu API Zalo response : access_token đã hết hạn hoặc không còn khả dụng và chưa retry
        if ((zaloZNSResponse.getError() == -220
                || "access_token is expired or removed".equalsIgnoreCase(zaloZNSResponse.getMessage()))
                && !retry) {
            // renew access token
            OaAccessTokenResponse oaAccessTokenResponse = renewAccessToken();
            if (oaAccessTokenResponse != null && oaAccessTokenResponse.getAccessToken() != null) {

                // Retry lại với access token mới
                return sendZaloZNS(request, oaAccessTokenResponse.getAccessToken(), true);
            }
        }
        // Nếu API Zalo response : Lỗi tài khoản Zalo không tồn tại
        else if (zaloZNSResponse.getError() == -118
                || "Zalo account not existed".equalsIgnoreCase(zaloZNSResponse.getMessage())) {
            return ZaloZNSResponse.builder()
                    .error(Integer.parseInt(ErrorCode.ZALO_ZNS_ACCOUNT_NOT_EXIST_ERROR.getCode()))
                    .message(ErrorCode.ZALO_ZNS_ACCOUNT_NOT_EXIST_ERROR.getMessage())
                    .data(null)
                    .build();
        } else if (zaloZNSResponse.getData() == null) {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        // Ghi log Zalo ZNS
        insertZaloZnsLog(request, accessToken, zaloZNSResponse);

        return zaloZNSResponse;
    }

    /**
     * Dùng Refresh Token để lấy Access Token.
     * </br>
     * Access Token có hiệu lực 25 giờ.
     * </br>
     * <a href="https://developers.zalo.me/docs/official-account/bat-dau/xac-thuc-va-uy-quyen-cho-ung-dung-new">...</a>
     *
     * @return {@link OaAccessTokenResponse}
     */
    @Override
    public OaAccessTokenResponse renewAccessToken() {

        HttpHeaders header = buildHeaderOaAccessToken();

        MultiValueMap<String, String> formData = buildRequestBodyOaAccessToken();

        OaAccessTokenResponse oaAccessTokenResponse = callApiOaAccessToken(header, formData);

        if (oaAccessTokenResponse != null
                && oaAccessTokenResponse.getAccessToken() != null
                && oaAccessTokenResponse.getRefreshToken() != null) {
            saveNewAccessTokenAndRefreshToken(oaAccessTokenResponse);
        } else {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        log.info(String.valueOf(oaAccessTokenResponse));

        return oaAccessTokenResponse;
    }

    private HttpHeaders buildHeaderZaloZNS(String accessToken) {

        HttpHeaders header = new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_JSON);
        header.set("access_token", accessToken);

        return header;
    }

    private Map<String, Object> buildRequestBodyZaloZNS(ZaloZNSRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("mode", request.getMode());
        body.put("phone", request.getPhone());
        body.put("template_id", request.getTemplateId());
        body.put("template_data", request.getTemplateData());
        body.put("tracking_id", request.getTrackingId());

        if (body == null || body.isEmpty()) {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

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

        logService.logBusiness(String.valueOf(response.getBody()));

        if (!response.getStatusCode().is2xxSuccessful()) {
            logService.logBusiness(
                    String.format("Failed to send ZNS request. Status: %s, Body: %s",
                            response.getStatusCode(),
                            response.getBody() != null ? response.getBody() : "null")
            );

            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        return response.getBody();
    }

    private void insertZaloZnsLog(ZaloZNSRequest request, String accessToken, ZaloZNSResponse response) {

        ZaloZnsLog zaloZnsLog = ZaloZnsLog.builder().accessToken(accessToken)
                .phone(request.getPhone())
                .templateId(request.getTemplateId())
                .templateData(request.getTemplateData().toString())
                .trackingId(request.getTrackingId())
                .error(response.getError())
                .message(response.getMessage())
                .msgId(response.getData().getMsgId())
                .sentTime(response.getData().getSentTime())
                .dailyQuota(response.getData().getQuota().getDailyQuota())
                .remainingQuota(response.getData().getQuota().getRemainingQuota())
                .build();

        zaloZnsLogMapper.insert(zaloZnsLog);
    }

    private HttpHeaders buildHeaderOaAccessToken() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("secret_key", zaloZnsConfig.getSecretKeyZaloZNS());

        return header;
    }

    private MultiValueMap<String, String> buildRequestBodyOaAccessToken() {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        String refreshToken = zaloOaTokenMapper.findLatestToken().getRefreshToken();

        formData.add("refresh_token", refreshToken);
        formData.add("app_id", zaloZnsConfig.getAppIdZaloZNS());
        formData.add("grant_type", "refresh_token");

        if (formData.isEmpty()) {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        return formData;
    }

    private OaAccessTokenResponse callApiOaAccessToken(HttpHeaders headers, MultiValueMap<String, String> formData) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        String url = zaloZnsConfig.getUrlRefreshToken();

        ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                request,
                String.class
        );

        log.info(String.valueOf(response));

        if (!response.getStatusCode().is2xxSuccessful()) {
            logService.logBusiness(
                    String.format("Failed to refresh token. Status: %s, Body: %s",
                            response.getStatusCode(),
                            response.getBody() != null ? response.getBody() : "null")
            );

            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            // Nếu có field "error" và khác 0 (OK) => ném exception
            if (root.has("error") && root.get("error").asInt() != 0) {
                logService.logBusiness(
                        String.format("Zalo API error. Code: %s, Name: %s, Desc: %s",
                                root.path("error").asInt(),
                                root.path("error_name").asText(),
                                root.path("error_description").asText())
                );

                throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
            }

            // Không có error => parse sang response object
            return mapper.treeToValue(root, OaAccessTokenResponse.class);

        } catch (Exception e) {
            logService.logBusiness(
                    String.format("Failed to parse refresh token response. Body: %s", response.getBody())
            );

            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }
    }

    /**
     * Lưu access token - refresh token - expires in mới từ API Zalo trả về vào table zalo_oa_token
     */
    private void saveNewAccessTokenAndRefreshToken(OaAccessTokenResponse response) {

        if (response == null || response.getAccessToken() == null || response.getRefreshToken() == null) {
            throw new AppException(ErrorCode.ZALO_ZNS_ERROR);
        }

        ZaloOaToken zaloOaToken = ZaloOaToken.builder()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .expiresIn(response.getExpiresIn())
                .build();
        AuditUtil.insert(zaloOaToken);

        zaloOaTokenMapper.insert(zaloOaToken);
    }
}
