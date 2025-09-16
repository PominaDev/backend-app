package com.pomina.commonservices.user_activity.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestUtils {

    /**
     *
     * @param request
     * @return
     */
    public static String extractQueryParams(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap.isEmpty()) return null;
        return paramMap.entrySet().stream()
                .map(e -> e.getKey() + "=" + Arrays.toString(e.getValue()))
                .collect(Collectors.joining("&"));
    }

    /**
     *
     * @param request
     * @return
     */
    public static String extractRequestBody(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper wrapper) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    return new String(buf, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    return "[Unsupported Encoding]";
                }
            }
        }
        return null;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String extractRequestParams(HttpServletRequest request) {
        String body = extractRequestBody(request);
        if (body != null && !body.isEmpty()) return body;
        return extractQueryParams(request);
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
