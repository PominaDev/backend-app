package com.pomina.webapp.menu_permission.dto.request;

import com.pomina.webapp.menu_permission.entity.MasterMenu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterPermissionRequestDto {
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
    private MasterMenu masterMenu;
}
