package com.pomina.appbaohanh.menu_permission.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterPermissionRequestDto {

    private Integer masterMenuId;
    private Long sysUserId;
    private Boolean isFull;
    private Boolean isInsert;
    private Boolean isDelete;
    private Boolean isUpdate;
    private Boolean isRead;
    private Boolean isPrint;
    private Boolean isExport;
}
