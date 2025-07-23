package com.pomina.erpapp.appbaohanh.common.utils;

import java.util.regex.Pattern;

public class PhoneUtil {

    private PhoneUtil() {}

    // E.164 format: +84xxxxxxxxx
    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    /**
     * Kiểm tra số điện thoại đã ở định dạng quốc tế E.164.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && E164_PATTERN.matcher(phoneNumber).matches();
    }

    /**
     * Chuẩn hóa số điện thoại người dùng nhập thành định dạng E.164.
     * Hỗ trợ các định dạng như: 03XXX, 843XXX, +843XXX
     */
    public static String normalizePhoneNumber(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        String cleaned = input.trim().replaceAll("[^\\d+]", "");

        if (cleaned.startsWith("+84")) {
            return cleaned;
        }

        if (cleaned.startsWith("84")) {
            return "+" + cleaned;
        }

        if (cleaned.startsWith("0")) {
            return "+84" + cleaned.substring(1);
        }
        return cleaned;
    }
}
