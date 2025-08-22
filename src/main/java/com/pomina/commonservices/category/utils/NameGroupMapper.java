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

    private static final Pattern NON_ALNUM_SPACE = Pattern.compile("[^a-zA-Z0-9\\s]");

    public static String mapNameGroupToKey(String nameGroup) {
        if (nameGroup == null || nameGroup.isBlank()) {
            return "";
        }

        // 1. Chuẩn hóa bỏ dấu tiếng Việt, thay các ký tự đặc biệt
        String normalized = Normalizer.normalize(nameGroup, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("đ", "d")
                .replace("Đ", "d")
                .replace("â", "a")
                .replace("Â", "a")
                .replace("ă", "a")
                .replace("Ă", "a")
                .replace("ê", "e")
                .replace("Ê", "e")
                .replace("ô", "o")
                .replace("Ô", "o")
                .replace("ơ", "o")
                .replace("Ơ", "o")
                .replace("ư", "u")
                .replace("Ư", "u");

        // 2. Loại ký tự đặc biệt, chuyển thường, trim và gộp space
        normalized = NON_ALNUM_SPACE.matcher(normalized).replaceAll("")
                .toLowerCase()
                .replaceAll("\\s+", " ")
                .trim();

        // 3. Tách từ và build camelCase
        String[] words = normalized.split("\\s+");
        if (words.length == 0 || words[0].isEmpty()) {
            return "";
        }

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
