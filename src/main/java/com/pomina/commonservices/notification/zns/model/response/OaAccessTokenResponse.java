package com.pomina.commonservices.notification.zns.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OaAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    /*
        Access token dùng để gọi các Official Account API
        Hiệu lực: 25 giờ
     */

    @JsonProperty("refresh_token")
    private String refreshToken;
    /*
        Mỗi access token được tạo sẽ có một refresh token đi kèm. Refresh token cho phép bạn tạo lại access token mới khi access token hiện tại hết hiệu lực. Refresh token chỉ có thể sử dụng 1 lần.
        Hiệu lực: 3 tháng
     */

    @JsonProperty("expires_in")
    private String expiresIn;
    /*
        Thời hạn của access token (đơn vị tính: giây)
     */
}
