package com.pomina.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HmacUtil {

    private final String hmacAlgorithm;
    private final String secretKey;
    private final long maxTimeDifferenceMillis;

    public HmacUtil(
            @Value("${security.hmac.algorithm}") String hmacAlgorithm,
            @Value("${security.hmac.secret}") String secretKey,
            @Value("${security.hmac.max-time-diff}") long maxTimeDifferenceMillis) {
        this.hmacAlgorithm = hmacAlgorithm;
        this.secretKey = secretKey;
        this.maxTimeDifferenceMillis = maxTimeDifferenceMillis;
    }

    /**
     * Generates HMAC signature for the given data
     * @param data Data to be signed
     * @return Base64 encoded HMAC signature
     */
    public String generateHmac(String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), hmacAlgorithm);
            Mac mac = Mac.getInstance(hmacAlgorithm);
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate HMAC signature", e);
        }
    }

    /**
     * Verifies HMAC signature
     * @param data Original data
     * @param signature Received signature to verify
     * @return true if signature is valid
     */
    public boolean verifyHmac(String data, String signature) {
        String expectedSignature = generateHmac(data);
        return constantTimeEquals(expectedSignature, signature);
    }

    /**
     * Constant time comparison to prevent timing attacks
     */
    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        byte[] aBytes = a.getBytes(StandardCharsets.UTF_8);
        byte[] bBytes = b.getBytes(StandardCharsets.UTF_8);

        if (aBytes.length != bBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < aBytes.length; i++) {
            result |= aBytes[i] ^ bBytes[i];
        }
        return result == 0;
    }

    /**
     * Validates timestamp to prevent replay attacks
     * @param timestamp Timestamp from request
     * @throws SecurityException if timestamp is invalid
     */
    public void validateTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = Math.abs(currentTime - timestamp);

        if (timeDifference > maxTimeDifferenceMillis) {
            throw new SecurityException("Invalid timestamp");
        }
    }
}
