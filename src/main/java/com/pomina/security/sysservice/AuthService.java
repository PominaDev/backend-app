package com.pomina.security.sysservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.security.config.JwtAuthentication;
import com.pomina.security.config.JwtIssuer;
import com.pomina.security.config.UserPrincipal;
import com.pomina.security.mapper.SysUserMapper;
import com.pomina.security.model.SysUser;
import com.pomina.security.sysmodel.LoginRequest;
import com.pomina.security.sysmodel.LoginResponse;
import com.pomina.security.sysmodel.LogoutRequest;
import com.pomina.security.sysmodel.RefreshTokenRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SysUserMapper sysUserMapper;

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    private final RedisTokenService redisTokenService;

    private final TokenBlacklistService blacklistService;

    public LoginResponse attemptLogin(LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            String accessToken = jwtIssuer.issue(JwtIssuer.Request.builder()
                    .userId(userPrincipal.getUserId())
                    .userName(userPrincipal.getUsername())
                    .fullName(userPrincipal.getHoVaTen())
                    .phoneNumber(userPrincipal.getPhoneNumber())
                    .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .tokenType(JwtIssuer.TokenType.ACCESS_TOKEN)
                    .build());

            String refreshToken = jwtIssuer.issue(JwtIssuer.Request.builder()
                    .userId(userPrincipal.getUserId())
                    .userName(userPrincipal.getUsername())
                    .fullName(userPrincipal.getHoVaTen())
                    .phoneNumber(userPrincipal.getPhoneNumber())
                    .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .tokenType(JwtIssuer.TokenType.REFRESH_TOKEN)
                    .build());

            redisTokenService.saveToken(
                    userPrincipal.getUserId(),
                    loginRequest.getDeviceId(),
                    refreshToken,
                    loginRequest.getUserAgent(),
                    jwtIssuer.getRefreshTokenExpiration()
            );

            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .username(userPrincipal.getUsername())
                    .fullName(userPrincipal.getHoVaTen())
                    .phoneNumber(userPrincipal.getPhoneNumber())
                    .roleName(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .build();
    }

    public Void attemptLogout(LogoutRequest logoutRequest, HttpServletRequest httpServletRequest) {
        String accessToken = extractAccessToken(httpServletRequest);
        if (accessToken == null) return null;

        DecodedJWT jwt;
        try {
            jwt = JWT.decode(accessToken);
        } catch (JWTDecodeException e) {
            // Token invalid format
            return null;
        }

        // Nếu token đã hết hạn, không cần xử lý thêm
        if (jwt.getExpiresAt().before(new Date())) {
            return null;
        }

        // Lấy thông tin người dùng
        Integer userId = JwtAuthentication.getCurrentUserId(); // code bạn đã có
        String deviceId = logoutRequest.getDeviceId();
        String userAgent = logoutRequest.getUserAgent();

        if (userId != null && deviceId != null && userAgent != null) {
            var tokenInfo = redisTokenService.getKey(userId, deviceId, userAgent);
            if (tokenInfo != null && tokenInfo.getUserAgent().equals(userAgent)) {
                Instant exp = jwt.getExpiresAt().toInstant();
                blacklistService.blacklistToken(accessToken, exp);
            }
        }

        return null;
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String deviceId = request.getDeviceId();
        String userAgent = request.getUserAgent();

        DecodedJWT decodedJwt = jwtIssuer.verify(refreshToken);

        Integer userId = Integer.valueOf(decodedJwt.getSubject());

        if (!redisTokenService.isValidRefreshToken(userId, deviceId, refreshToken, userAgent)) {
            throw new JWTVerificationException(null);
        }

        SysUser sysUser = sysUserMapper.findByUserId(userId);
        if (sysUser == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        var authorities = List.of(sysUser.getRoleName());

        String newAccessToken = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(userId)
                .userName(sysUser.getUsername())
                .fullName(sysUser.getHoVaTen())
                .phoneNumber(sysUser.getPhoneNumber())
                .roles(authorities)
                .tokenType(JwtIssuer.TokenType.ACCESS_TOKEN)
                .build());

        String newRefreshToken = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(userId)
                .userName(sysUser.getUsername())
                .fullName(sysUser.getHoVaTen())
                .phoneNumber(sysUser.getPhoneNumber())
                .roles(authorities)
                .tokenType(JwtIssuer.TokenType.REFRESH_TOKEN)
                .build());

        redisTokenService.saveToken(userId,
                deviceId,
                newRefreshToken,
                userAgent,
                jwtIssuer.getRefreshTokenExpiration());

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .username(sysUser.getUsername())
                .fullName(sysUser.getHoVaTen())
                .phoneNumber(sysUser.getPhoneNumber())
                .roleName(authorities)
                .build();
    }

    private String extractAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}