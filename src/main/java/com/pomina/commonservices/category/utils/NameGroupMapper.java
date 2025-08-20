package com.pomina.commonservices.category.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Description: Util for name group mapper.
 * </br>
 * Example: "độ mạ" -> "doMa".
 */
public class NameGroupMapper {

    private NameGroupMapper() {}

    private static final Pattern DIACRITICS = Pattern.compile("\\p{M}");
    private static final Pattern NON_ALNUM_SPACE = Pattern.compile("[^a-zA-Z0-9\\s]+");
    private static final Pattern MULTI_SPACE = Pattern.compile("\\s+");

    public static String mapNameGroupToKey(String nameGroup) {
        if (nameGroup == null || nameGroup.isBlank()) {
            return "";
        }

        // 1. Chuẩn hóa bỏ dấu tiếng Việt
        String normalized = Normalizer.normalize(nameGroup, Normalizer.Form.NFD);
        normalized = DIACRITICS.matcher(normalized).replaceAll("");

        // 2. Loại ký tự đặc biệt, chuyển thường, trim và gộp space
        normalized = NON_ALNUM_SPACE.matcher(normalized).replaceAll("");
        normalized = normalized.toLowerCase().trim();
        normalized = MULTI_SPACE.matcher(normalized).replaceAll(" ");

        // 3. Tách từ và build camelCase
        String[] words = normalized.split(" ");
        if (words.length == 0) return "";

        StringBuilder camelCase = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                camelCase.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1));
            }
        }
        return camelCase.toString();
    }
}
