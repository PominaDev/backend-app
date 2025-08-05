package com.pomina.common.utils;

import com.pomina.common.enums.AuditStatus;
import com.pomina.common.model.BaseEntity;
import com.pomina.security.config.JwtAuthentication;

import java.time.LocalDateTime;

public class AuditUtil {

    private AuditUtil() {}

    private static final Integer DEFAULT_USER = -8386; // Default user id

    private static final boolean DEFAULT_IS_DELETED = false;

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static AuditStatus getDefaultStatus() {
        return AuditStatus.ACTIVE;
    }

    public static boolean getDefaultIsDeleted() {
        return DEFAULT_IS_DELETED;
    }

    public static Integer getDefaultUser() {
        Integer userId = JwtAuthentication.getCurrentUserId();
        if (userId == null) return DEFAULT_USER;
        return userId;
    }

    public static void insert(BaseEntity entity) {
        if (entity == null) return;
        applyInsertInfo(entity);
        entity.setStatus(getDefaultStatus().getStatus());
        entity.setIsDeleted(getDefaultIsDeleted());
    }

    public static void update(BaseEntity entity) {
        if (entity == null) return;
        applyUpdateInfo(entity, null);
    }

    public static void softDelete(BaseEntity entity, String note) {
        if (entity == null) return;
        entity.setIsDeleted(true);
        entity.setStatus(AuditStatus.DELETED.getStatus());
        applyUpdateInfo(entity, note);
    }

    public static void markAsActive(BaseEntity entity) {
        if (entity == null) return;
        entity.setStatus(getDefaultStatus().getStatus());
        entity.setIsDeleted(getDefaultIsDeleted());
    }

    public static void deactivate(BaseEntity entity, String note) {
        if (entity == null) return;
        entity.setStatus(AuditStatus.INACTIVE.getStatus());
        applyUpdateInfo(entity, note);
    }

    public static void reactivate(BaseEntity entity, String note) {
        if (entity == null) return;
        entity.setStatus(AuditStatus.ACTIVE.getStatus());
        entity.setIsDeleted(DEFAULT_IS_DELETED);
        applyUpdateInfo(entity, note);
    }

    public static void restrict(BaseEntity entity, String note) {
        if (entity == null) return;
        entity.setStatus(AuditStatus.RESTRICTED.getStatus());
        applyUpdateInfo(entity, note);
    }

    public static void custom(BaseEntity entity, String status, String note) {
        if (entity == null) return;
        if (status != null) {
            entity.setStatus(AuditStatus.valueOf(status).getStatus());
        }
        applyUpdateInfo(entity, note);
    }

    private static void applyUpdateInfo(BaseEntity entity, String note) {
        entity.setUpdatedAt(now());
        entity.setUpdatedBy(getDefaultUser());
        if (note != null) {
            entity.setNote(note);
        }
    }

    private static void applyInsertInfo(BaseEntity entity) {
        entity.setCreatedAt(now());
        entity.setCreatedBy(getDefaultUser());
    }
}
