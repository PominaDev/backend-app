package com.pomina.common.utils;

import com.pomina.common.model.BaseEntity;

import java.util.List;

// TODO: Low performance
public class AuditBatchUtil {

    private AuditBatchUtil() {}

    /**
     * Áp dụng insert cho cả list entity
     */
    public static void insertAll(List<? extends BaseEntity> entities) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(AuditUtil::insert);
    }

    /**
     * Áp dụng update cho cả list entity
     */
    public static void updateAll(List<? extends BaseEntity> entities) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(AuditUtil::update);
    }

    /**
     * Áp dụng soft delete cho cả list entity
     */
    public static void softDeleteAll(List<? extends BaseEntity> entities, String note) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(e -> AuditUtil.softDelete(e, note));
    }

    /**
     * Đánh dấu active lại cho cả list
     */
    public static void markAsActiveAll(List<? extends BaseEntity> entities) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(AuditUtil::markAsActive);
    }

    /**
     * Deactivate list entity
     */
    public static void deactivateAll(List<? extends BaseEntity> entities, String note) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(e -> AuditUtil.deactivate(e, note));
    }

    /**
     * Reactivate list entity
     */
    public static void reactivateAll(List<? extends BaseEntity> entities, String note) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(e -> AuditUtil.reactivate(e, note));
    }

    /**
     * Restrict list entity
     */
    public static void restrictAll(List<? extends BaseEntity> entities, String note) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(e -> AuditUtil.restrict(e, note));
    }

    /**
     * Custom status cho list entity
     */
    public static void customAll(List<? extends BaseEntity> entities, String status, String note) {
        if (entities == null || entities.isEmpty()) return;
        entities.forEach(e -> AuditUtil.custom(e, status, note));
    }
}
