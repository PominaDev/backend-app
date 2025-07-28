package com.pomina.appbaohanh.menu_permission.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MasterPermissionResponseDto {
    private Integer masterPermissionId;
    private Integer masterMenuId;
    private Long sysUserId;
    private Boolean isFull;
    private Boolean isInsert;
    private Boolean isDelete;
    private Boolean isUpdate;
    private Boolean isRead;
    private Boolean isPrint;
    private Boolean isExport;
    private String status;
    private String note;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
