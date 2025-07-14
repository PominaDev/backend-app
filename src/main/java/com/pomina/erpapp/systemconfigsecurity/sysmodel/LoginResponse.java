package com.pomina.erpapp.systemconfigsecurity.sysmodel;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {
    private final String accessToken;

    // User information
    private final String username;
    private final String hoVaTen;
    private final String phoneNumber;
    private final List<String> roleName;
}
