package com.pomina.common.logging;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // đảm bảo filter này chạy đầu tiên
public class RequestLoggingFilter implements Filter {

    private static final long SLOW_THRESHOLD = 1000;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String uri = request.getRequestURI();
        long start = System.currentTimeMillis();

        try {
            chain.doFilter(servletRequest, servletResponse);
            long elapsed = System.currentTimeMillis() - start;

            if (elapsed > SLOW_THRESHOLD) {
                log.warn("SLOW: {} {} took {} ms", method, uri, elapsed);
            } else {
                log.info("REQUEST: {} {} took {} ms", method, uri, elapsed);
            }
        } catch (Exception ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.error("ERROR: {} {} threw {} after {} ms",
                    method, uri, ex.getClass().getSimpleName(), elapsed, ex);
            throw ex;
        }
    }
}