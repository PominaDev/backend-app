package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import com.pomina.erpapp.appbaohanh.common.model.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterPermissionUpdateDto{

    @PositiveOrZero(message = "masterPermissionId must be a positive number or 0")
    @NotNull(message = "masterPermissionId is required")
    private Integer masterPermissionId;

    @Positive(message = "masterMenuId must be a positive number")
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

    @Size(max = 255, message = "Status menu name must not exceed 255 characters")
    private String status;

    @Size(max = 255, message = "Noted menu name must not exceed 255 characters")
    private String noted;

    @Size(max = 255, message = "createdBy menu name must not exceed 255 characters")
    private String createdBy;

    @Size(max = 255, message = "UpdatedBy menu name must not exceed 255 characters")
    private String updatedBy;
}
