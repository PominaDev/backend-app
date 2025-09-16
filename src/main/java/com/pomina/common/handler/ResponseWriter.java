package com.pomina.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomina.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handler response error from filter authentication
 */
public class ResponseWriter {

    private ResponseWriter() {
    }

    public static void writeJsonError(HttpServletResponse response,
                                      ErrorCode errorCode,
                                      int httpStatus,
                                      boolean useErrorCode) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json;charset=UTF-8");

        String code = useErrorCode ? errorCode.getCode() : String.valueOf(httpStatus);

        ApiResponse<Object> errorResponse = new ApiResponse<>(
                false,
                errorCode.getMessage(),
                code,
                null,
                LocalDateTime.now()
        );

        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}