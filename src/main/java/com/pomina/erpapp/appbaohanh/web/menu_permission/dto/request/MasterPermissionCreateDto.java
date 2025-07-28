package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Size(max = 255, message = "Status menu name must not exceed 255 characters")
    private String status;

    @Size(max = 255, message = "Noted menu name must not exceed 255 characters")
    private String noted;

    @Size(max = 255, message = "createdBy menu name must not exceed 255 characters")
    private String createdBy;
}
