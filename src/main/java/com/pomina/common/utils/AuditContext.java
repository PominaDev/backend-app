package com.pomina.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public final class AuditContext {
    private static final ThreadLocal<AuditInfo> CONTEXT = new ThreadLocal<>();

    private AuditContext() {}

    public static void set(AuditInfo info) { CONTEXT.set(info); }
    public static AuditInfo get() { return CONTEXT.get(); }
    public static void clear() { CONTEXT.remove(); }

    public static final class AuditInfo {
        private final Long userId;

        public AuditInfo(Long userId) {
            this.userId = userId;
        }
        public Long getUserId() { return this.userId; }
    }
}
