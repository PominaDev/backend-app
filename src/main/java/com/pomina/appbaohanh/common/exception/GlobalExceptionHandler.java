package com.pomina.appbaohanh.common.exception;

import com.pomina.appbaohanh.common.handler.ApiResponse;
import com.pomina.appbaohanh.common.handler.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException e) {
        return ResponseHandler.error(e.getErrorCode());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleOther(Exception ex) {
//        return ResponseHandler.error(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseHandler.error(msg, ErrorCode.INTERNAL_ERROR, HttpStatus.BAD_REQUEST);
    }
}
