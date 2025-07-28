package com.pomina.common.utils;

import com.pomina.common.config.SensitiveData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class SensitiveDataUtil {

    private final HmacUtil hmacUtil;

    @Value("${security.hmac.secret}")
    private String encryptionKey;

    public void encryptSensitiveFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(SensitiveData.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value != null) {
                        // Mã hóa giá trị
                        String encrypted = encrypt(value.toString());
                        field.set(obj, encrypted);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to encrypt sensitive field", e);
                }
            }
        }
    }

    public void decryptSensitiveFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(SensitiveData.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value != null) {
                        // Giải mã giá trị
                        String decrypted = decrypt(value.toString());
                        field.set(obj, decrypted);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to decrypt sensitive field", e);
                }
            }
        }
    }

    private String encrypt(String data) {
        // Triển khai mã hóa AES hoặc thuật toán phù hợp
        // Ví dụ đơn giản:
        return "ENC:" + hmacUtil.generateHmac(data);
    }

    private String decrypt(String encryptedData) {
        // Logic giải mã tương ứng
        if (encryptedData.startsWith("ENC:")) {
            // Thực tế cần triển khai giải mã thực sự
            return encryptedData.substring(4);
        }
        return encryptedData;
    }
}
