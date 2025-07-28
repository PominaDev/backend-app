package com.pomina.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    /**
     * Secret key used for issuing JWT
     */
    private String secretKey;

    private Long accessTokenExpiration;

    private Long refreshTokenExpiration;
}
