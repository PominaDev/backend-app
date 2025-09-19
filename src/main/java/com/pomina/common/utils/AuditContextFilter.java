package com.pomina.common.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pomina.security.config.JwtAuthentication;

import java.io.IOException;

@Component
public class AuditContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws IOException, ServletException {
        Long userId = null;
        try {
            Integer uid = JwtAuthentication.getCurrentUserId();
            if (uid != null) userId = uid.longValue();
        } catch (Exception ignore) {
            System.out.println("AuditContextFilter.class : fail to create a ThreadLocal to AuditContextFilter");
        }
        try {
            AuditContext.set(new AuditContext.AuditInfo(userId));
            chain.doFilter(req, res);
        } finally {
            AuditContext.clear();
        }
    }

}
