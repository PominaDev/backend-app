package com.pomina.erpapp.systemconfigsecurity.sysmodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String accessToken;
}
