package com.pomina.webapp.menu_permission.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterPermissionCreateDto {

    @Positive(message = "masterMenuId must be a positive number")
    @NotNull(message = "masterMenuId is required")
    private Integer masterMenuId;

    @Positive(message = "sysUserId must be a positive number")
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
