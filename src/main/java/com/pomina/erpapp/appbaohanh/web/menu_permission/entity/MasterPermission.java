package com.pomina.erpapp.appbaohanh.web.menu_permission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterPermission {

    private Integer masterPermissionId;
    private Integer masterMenuId;
    private Integer sysUserId;
    private Boolean isFull;
    private Boolean isInsert;
    private Boolean isDelete;
    private Boolean isUpdate;
    private Boolean isRead;
    private Boolean isPrint;
    private Boolean isExport;
    private String status;
    private String note;

    // Audit fields
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;

    // Fields from LEFT JOIN queries
    private String username;
    private String fullName;
    private String phoneNumber;

    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Integer orderIndex;
    private Integer parentId;
    private String menuStatus;
    private String menuNote;
}
