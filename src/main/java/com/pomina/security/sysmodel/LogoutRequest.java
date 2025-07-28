package com.pomina.security.sysmodel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequest {

    @NotBlank
    private String deviceId;

    private String userAgent;
}
