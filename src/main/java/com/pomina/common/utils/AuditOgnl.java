package com.pomina.common.utils;

import java.time.LocalDateTime;

public final class AuditOgnl {
    private AuditOgnl() {}
    public static Long userId() {
        AuditContext.AuditInfo ai = AuditContext.get();
        return ai != null ? ai.getUserId() : null;
    }
    public static LocalDateTime userTimeNow() {
        return LocalDateTime.now();
    }
}
