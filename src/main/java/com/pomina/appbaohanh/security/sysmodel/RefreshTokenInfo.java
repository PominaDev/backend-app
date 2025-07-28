package com.pomina.appbaohanh.security.sysmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenInfo implements Serializable {

    private String encryptedToken;

    private String deviceId;

    private String userAgent;

    private Instant expiryDate;
}
