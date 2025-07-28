package com.pomina.appbaohanh.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_ERROR("5000", "Lỗi hệ thống"),
    USER_EXISTED("2001", "Người dùng đã tồn tại"),
    USER_NOT_FOUND("2000", "Người dùng không tồn tại"),
    LOCATION_NOT_FOUND("4000", "Lỗi không tìm thấy vị trí"),
    INVALID_PHONE_NUMBER("3000", "Lỗi sai định dạng số điện thoại"),
    VONAGE_ERROR("5001", "Lỗi từ Vonage"),
    VERIFICATION_FAILED("5000", "Xác minh thất bại"),
    OTP_EXPIRED("5000", "Mã OTP đã hết hạn"),
    TOKEN_EXPIRED("5000", "Token đã hết hạn"),
    INVALID_REFRESH_TOKEN("5000", "Lỗi refresh token"),
    GOOGLE_API_ERROR("5000", "Lỗi từ google geocode"),
    ;

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
