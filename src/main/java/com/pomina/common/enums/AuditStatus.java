package com.pomina.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Hủy kích hoạt"),
    DELETED("DELETED", "Đã xóa"),
    RESTRICTED("ACTIVE", "Xóa mềm"),
    ;

    private final String status;
    private final String description;
}
