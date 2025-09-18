package com.pomina.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    // Định nghĩa múi giờ
    private static final ZoneId ZONE_VN = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final ZoneId ZONE_UTC = ZoneOffset.UTC;

    // Formatter cho parse và format nếu dùng String
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Convert từ giờ VN sang UTC
     */
    public static LocalDateTime convertVNToUTC(LocalDateTime vnDateTime) {
        if (vnDateTime == null) return null;
        ZonedDateTime vnZoned = vnDateTime.atZone(ZONE_VN);
        return vnZoned.withZoneSameInstant(ZONE_UTC).toLocalDateTime();
    }

    /**
     * Convert từ UTC sang giờ VN
     */
    public static LocalDateTime convertUTCToVN(LocalDateTime utcDateTime) {
        if (utcDateTime == null) return null;
        ZonedDateTime utcZoned = utcDateTime.atZone(ZONE_UTC);
        return utcZoned.withZoneSameInstant(ZONE_VN).toLocalDateTime();
    }

    /**
     * Convert String giờ VN sang UTC (format yyyy-MM-dd HH:mm:ss)
     */
    public static LocalDateTime convertVNToUTC(String vnDateTimeStr) {
        if (vnDateTimeStr == null || vnDateTimeStr.isEmpty()) return null;
        LocalDateTime vnDateTime = LocalDateTime.parse(vnDateTimeStr, FORMATTER);
        return convertVNToUTC(vnDateTime);
    }

    /**
     * Convert String giờ UTC sang VN (format yyyy-MM-dd HH:mm:ss)
     */
    public static LocalDateTime convertUTCToVN(String utcDateTimeStr) {
        if (utcDateTimeStr == null || utcDateTimeStr.isEmpty()) return null;
        LocalDateTime utcDateTime = LocalDateTime.parse(utcDateTimeStr, FORMATTER);
        return convertUTCToVN(utcDateTime);
    }

    /**
     * Format LocalDateTime về String theo định dạng chuẩn
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(FORMATTER);
    }

    /**
     * Convert String sang LocalDateTime (format yyyy-MM-dd HH:mm:ss)
     */
    public static LocalDateTime parseToLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) return null;
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }

    /**
     * Convert String "yyyy-MM-dd HH:mm:ss" sang String "yyyy-MM-dd"
     */
    public static String convertToDateOnly(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) return null;

        // Parse thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);

        // Lấy phần ngày
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.toLocalDate().format(dateOnlyFormatter);
    }
}
