package com.pomina.app.menu_permission.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponseDto {
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
}
