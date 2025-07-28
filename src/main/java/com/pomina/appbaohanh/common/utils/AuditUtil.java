package com.pomina.appbaohanh.common.utils;

import com.pomina.appbaohanh.common.model.BaseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class AuditUtil {

    private AuditUtil() {}

    private static final String DEFAULT_USER = "system";
    private static final String DEFAULT_STATUS = "ACTIVE";
    private static final boolean DEFAULT_IS_DELETED = false;
    private static final String DELETED_STATUS = "DELETED";
    private static final String INACTIVE_STATUS = "INACTIVE";
    private static final String RESTRICTED_STATUS = "RESTRICTED";

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

    public static void insert(BaseEntity entity) {
        entity.setCreatedAt(now());
        entity.setCreatedBy(getCurrentUser());
        entity.setStatus(getDefaultStatus());
        entity.setIsDeleted(getDefaultIsDeleted());
    }

    public static void update(BaseEntity entity) {
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
    }

    public static void search(BaseEntity entity) {
        entity.setStatus(getDefaultStatus());
        entity.setIsDeleted(getDefaultIsDeleted());
    }

    public static void delete(BaseEntity entity, String note) {
        entity.setIsDeleted(true);
        entity.setStatus(DELETED_STATUS);
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
        if (note != null) {
            entity.setNote(note);
        }
    }

    public static void deactivate(BaseEntity entity, String note) {
        entity.setStatus(INACTIVE_STATUS);
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
        if (note != null) {
            entity.setNote(note);
        }
    }

    public static void reactivate(BaseEntity entity, String note) {
        entity.setStatus(DEFAULT_STATUS);
        entity.setIsDeleted(DEFAULT_IS_DELETED);
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
        if (note != null) {
            entity.setNote(note);
        }
    }

    public static void restrict(BaseEntity entity, String note) {
        entity.setStatus(RESTRICTED_STATUS);
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
        if (note != null) {
            entity.setNote(note);
        }
    }

    public static void custom(BaseEntity entity, String status, String note) {
        if (status != null) {
            entity.setStatus(status);
        }
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getCurrentUser());
        if (note != null) {
            entity.setNote(note);
        }
    }
}
