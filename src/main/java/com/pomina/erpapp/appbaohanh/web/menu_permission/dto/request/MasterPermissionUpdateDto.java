package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterPermissionUpdateDto {

    @NotNull(message = "masterMenuId is required")
    private Integer masterMenuId;
    @NotNull(message = "sysUserId is required")
    private Long sysUserId;

    @NotNull(message = "isFull is required")
    private Boolean isFull;

    @NotNull(message = "isInsert is required")
    private Boolean isInsert;

    @NotNull(message = "isDelete is required")
    private Boolean isDelete;

    @NotNull(message = "isUpdate is required")
    private Boolean isUpdate;

    @NotNull(message = "isRead is required")
    private Boolean isRead;

    @NotNull(message = "isPrint is required")
    private Boolean isPrint;

    @NotNull(message = "isExport is required")
    private Boolean isExport;
}
