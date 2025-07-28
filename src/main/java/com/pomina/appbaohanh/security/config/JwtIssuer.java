package com.pomina.appbaohanh.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pomina.appbaohanh.common.exception.AppException;
import com.pomina.appbaohanh.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    private final JWTVerifier verifier;

    public enum TokenType {
        ACCESS_TOKEN,
        REFRESH_TOKEN
    }

    public String issue(Request request) {

        Instant now = Instant.now();
        Instant expiresAt = null;

        if(request.tokenType == TokenType.ACCESS_TOKEN) {
            expiresAt = now.plusMillis(jwtProperties.getAccessTokenExpiration());
        } else if (request.tokenType == TokenType.REFRESH_TOKEN) {
            expiresAt = now.plusMillis(jwtProperties.getRefreshTokenExpiration());
        }

        assert expiresAt != null;
        return JWT.create()
                .withSubject(String.valueOf(request.userId))
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiresAt))
                .withClaim("username", request.userName)
                .withClaim("au", request.getRoles())
                .withClaim("fullName", request.getFullName())
                .withClaim("phoneNumber", request.getPhoneNumber())
                .withClaim("tokenType", request.getTokenType().toString())
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

    public JwtIssuer(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
        this.verifier = JWT.require(algorithm)
                .build();
    }

    public DecodedJWT verify(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }

    public long getRefreshTokenExpiration() {
        return jwtProperties.getAccessTokenExpiration();
    }

    @Getter
    @Builder
    public static class Request {
        private final Integer userId;
        private final String userName;
        private final String fullName;
        private final String phoneNumber;
        private final List<String> roles;
        private final TokenType tokenType;
    }
}
