package com.pomina.security.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.handler.ResponseWriter;
import com.pomina.security.service.SysUserService;
import com.pomina.security.sysservice.TokenBlacklistService;
import com.pomina.security.utils.CachedBodyHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthentication extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;

    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    private final TokenBlacklistService tokenBlacklistService;

    private final SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String rawContentType = request.getContentType();
        String baseContentType = rawContentType != null ? rawContentType.split(";")[0].trim().toLowerCase() : "";

        HttpServletRequest effectiveRequest = request;

        if ("application/json".equals(baseContentType)) {
            CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);

            // Check user disable
            if (!checkUserActive(wrappedRequest, response)) {
                return;
            }

            effectiveRequest = wrappedRequest;
        }

        // Add filter for white list -> case /logout
        if (isWhiteListPath(request.getRequestURI())) {
            filterChain.doFilter(effectiveRequest, response);
            return;
        }

        // Token handling
        Optional<String> tokenOpt = extractTokenFromRequest(effectiveRequest);
        if (tokenOpt.isEmpty()) {
            filterChain.doFilter(effectiveRequest, response);
            return;
        }

        String token = tokenOpt.get();
        DecodedJWT jwt = validateJwt(token, response);
        if (jwt == null) {
            return;
        }

        // Check access token blacklist
        if (isAccessTokenBlacklisted(token, jwt)) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Set authentication
        SecurityContextHolder.getContext().setAuthentication(
                new UserPrincipalAuthenticationToken(jwtToPrincipalConverter.convert(jwt))
        );
        filterChain.doFilter(effectiveRequest, response);
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

    private boolean checkUserActive(CachedBodyHttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        String body = new String(request.getCachedBody(), StandardCharsets.UTF_8);
        if (!body.isEmpty()) {
            return isUserDisable(body, response);
        }
        return true;
    }

    private DecodedJWT validateJwt(String token, HttpServletResponse response) throws IOException {
        try {
            return jwtDecoder.decode(token);
        } catch (TokenExpiredException ex) {
            SecurityContextHolder.clearContext();
            ResponseWriter.writeJsonError(response, ErrorCode.TOKEN_EXPIRED, HttpServletResponse.SC_UNAUTHORIZED, false);
        } catch (JWTVerificationException ex) {
            SecurityContextHolder.clearContext();
            ResponseWriter.writeJsonError(response, ErrorCode.INVALID_TOKEN, HttpServletResponse.SC_UNAUTHORIZED, false);
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

    private boolean isWhiteListPath(String path) {
        return Arrays.stream(WebSecurityConfig.WHITE_LIST_ENDPOINTS).anyMatch(path::startsWith);
    }

    public boolean isUserDisable(String body, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(body);

        if (node.has("username")) {
            String username = node.get("username").asText();

            var sysUser = sysUserService.findByUserName(username).orElseThrow();
            if (Boolean.FALSE.equals(sysUser.getIsActive()) || Boolean.TRUE.equals(sysUser.getIsDeleted())) {
                ResponseWriter.writeJsonError(response, ErrorCode.ACCOUNT_DISABLED, HttpServletResponse.SC_UNAUTHORIZED, false);
                return false;
            }
        }
        return true;
    }

    public static String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserPrincipal userPrincipal) {
                return userPrincipal.getUsername();
            }
        }
        return null;
    }
}