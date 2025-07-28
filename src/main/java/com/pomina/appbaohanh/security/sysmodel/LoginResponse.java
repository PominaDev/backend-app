package com.pomina.appbaohanh.security.sysmodel;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {

    private final String accessToken;
    private final String refreshToken;

    // User information
    private final String username;
    private final String fullName;
    private final String phoneNumber;
    private final List<String> roleName;
}
