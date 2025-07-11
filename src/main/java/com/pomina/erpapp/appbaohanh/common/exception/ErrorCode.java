package com.pomina.erpapp.appbaohanh.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_ERROR("5000", "Lỗi hệ thống"),
    ;

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
