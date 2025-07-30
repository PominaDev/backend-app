package com.pomina.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Arrays;

@Component
public class EncryptionUtil {

    private final String secretKeyStr;
    private SecretKeySpec secretKey;

    public EncryptionUtil(@Value("${security.encryption.secret}") String secretKeyStr) {
        this.secretKeyStr = secretKeyStr;
    }

    @PostConstruct
        // Decode the secret key string into a byte array
    public void init() {
        try {
            // Giải mã key từ Base64 và lấy 16 byte đầu tiên (AES-128)
            byte[] keyBytes = Base64.getDecoder().decode(secretKeyStr);
            byte[] aesKey = Arrays.copyOf(keyBytes, 16);
            this.secretKey = new SecretKeySpec(aesKey, "AES");
        } catch (Exception e) {
            throw new RuntimeException("Encryption key initialization error", e);
        }
    }

    public String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }
}