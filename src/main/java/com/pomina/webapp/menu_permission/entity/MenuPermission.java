package com.pomina.webapp.menu_permission.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermission {
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
