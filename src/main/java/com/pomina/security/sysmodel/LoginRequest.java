package com.pomina.security.sysmodel;

import com.pomina.common.config.SensitiveData;
import com.pomina.common.utils.SensitiveDataUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    @SensitiveData
    private String password;

    @NotBlank
    private String deviceId;

    private String userAgent;
}
