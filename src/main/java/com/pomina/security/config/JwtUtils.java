package com.pomina.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

public class JwtUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String SECRET = "your-secret-key"; // đưa vào config

    public static Map<String, Object> decodeAndVerify(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) throw new AppException(ErrorCode.INVALID_TOKEN);

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
        String signature = parts[2];

        // verify signature
        String unsignedToken = parts[0] + "." + parts[1];
        String expectedSignature = sign(unsignedToken, SECRET);
        if (!expectedSignature.equals(signature)) throw new AppException(ErrorCode.INVALID_TOKEN);

        // parse payload
        Map<String, Object> payload = mapper.readValue(payloadJson, Map.class);

        // verify exp
        Long exp = ((Number) payload.get("exp")).longValue();
        if (exp * 1000 < System.currentTimeMillis()) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }

        return payload;
    }

    private static String sign(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(data.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
    }
}
