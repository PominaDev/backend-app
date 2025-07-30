package com.pomina.security.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.handler.ResponseWriter;
import com.pomina.security.sysservice.TokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthentication extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Optional<String> tokenOpt = extractTokenFromRequest(request);

        if (tokenOpt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenOpt.get();

        DecodedJWT jwt;

        try {
            jwt = jwtDecoder.decode(token);
        } catch (TokenExpiredException ex) {
            SecurityContextHolder.clearContext();
            ResponseWriter.writeJsonError(response, ErrorCode.TOKEN_EXPIRED, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        } catch (JWTVerificationException ex) {
            SecurityContextHolder.clearContext();
            ResponseWriter.writeJsonError(response, ErrorCode.INVALID_TOKEN, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        if (isAccessTokenBlacklisted(token, jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        var authentication = new UserPrincipalAuthenticationToken(
                jwtToPrincipalConverter.convert(jwt)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    public static Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserPrincipal userPrincipal) {
                return userPrincipal.getUserId();
            }
        }
        return null;
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }

    private boolean isAccessTokenBlacklisted(String token, DecodedJWT jwt) {
        String tokenType = jwt.getClaim("tokenType").asString();
        return "REFRESH_TOKEN".equals(tokenType)
                || tokenBlacklistService.isBlacklisted(token);
    }
}