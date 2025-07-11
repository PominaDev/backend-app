package com.pomina.erpapp.appbaohanh.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class AuditUtil {

    private static final String DEFAULT_USER = "system";
    private static final String DEFAULT_STATUS = "ACTIVE";
    private static final boolean DEFAULT_IS_DELETED = false;

    public static String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.isAuthenticated()) ? auth.getName() : DEFAULT_USER;
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static String getDefaultStatus() {
        return DEFAULT_STATUS;
    }

    public static boolean getDefaultIsDeleted() {
        return DEFAULT_IS_DELETED;
    }
}
