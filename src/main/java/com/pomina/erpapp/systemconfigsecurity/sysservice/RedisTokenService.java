package com.pomina.erpapp.systemconfigsecurity.sysservice;

import com.pomina.erpapp.systemconfigsecurity.sysmodel.RefreshTokenInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final RedisTemplate<String, RefreshTokenInfo> redisTemplate;

    public RefreshTokenInfo getKey(Long userId, String deviceId, String userAgent) {
        String key = buildKey(userId, deviceId, userAgent);
        return redisTemplate.opsForValue()
                .get(key);
    }

    public void saveToken(Long userId, String deviceId, String refreshToken, String userAgent, long expiryMs) {
        String key = buildKey(userId, deviceId, userAgent);
        RefreshTokenInfo info = RefreshTokenInfo.builder()
                .encryptedToken(encrypt(refreshToken))
                .deviceId(deviceId)
                .userAgent(userAgent)
                .expiryDate(Instant.now().plusMillis(expiryMs))
                .build();
        redisTemplate.opsForValue()
                .set(key, info, Duration.ofMillis(expiryMs));
    }

    public boolean isValidRefreshToken(Long userId, String deviceId, String refreshToken, String userAgent) {
        RefreshTokenInfo info = redisTemplate.opsForValue()
                .get(buildKey(userId, deviceId, userAgent));
        return info != null &&
                Objects.equals(info.getEncryptedToken(), encrypt(refreshToken)) &&
                Objects.equals(info.getUserAgent(), userAgent);
    }

    public void deleteToken(Long userId, String deviceId, String userAgent) {
        redisTemplate.delete(buildKey(userId, deviceId, userAgent));
    }

    private String encrypt(String raw) {
        return DigestUtils.sha256Hex(raw);
    }

    private String buildKey(Long userId, String deviceId, String userAgent) {
        return "refresh_token:" + userId + ":" + deviceId + ":" + userAgent;
    }
}