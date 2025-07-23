package com.pomina.erpapp.systemconfigsecurity.sysservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final RedisTemplate<String, String> redisTemplate;

    public void blacklistToken(String accessToken, Instant expirationTime) {
        Duration ttl = Duration.between(Instant.now(), expirationTime);
        if (!ttl.isNegative()) {
            String key = buildKey(accessToken);
            redisTemplate.opsForValue().set(key, "blacklisted", ttl);
        }
    }

    public boolean isBlacklisted(String accessToken) {
        return redisTemplate.hasKey(buildKey(accessToken));
    }

    private String buildKey(String token) {
        return "blacklist:" + token;
    }
}
