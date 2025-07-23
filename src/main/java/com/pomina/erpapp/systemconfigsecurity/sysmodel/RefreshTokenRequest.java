package com.pomina.erpapp.systemconfigsecurity.sysmodel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;

    @NotBlank
    private String deviceId;

    private String userAgent;
}
