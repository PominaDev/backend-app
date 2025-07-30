package com.pomina.common.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException e) {
        return ResponseHandler.error(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseHandler.error(msg, ErrorCode.INTERNAL_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseHandler.error(ErrorCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ApiResponse<Object>> handleJWTVerificationException(JWTVerificationException ex) {
        return ResponseHandler.error(ErrorCode.INVALID_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<ApiResponse<Object>> handleSQLException(SQLSyntaxErrorException ex) {
        return ResponseHandler.error(ErrorCode.BAD_SQL, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleOther(Exception ex) {
        return ResponseHandler.error(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
