package com.pomina.erpapp.appbaohanh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException{
    private final ErrorCode errorCode;
}
