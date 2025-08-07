package com.pomina.webapp.grant_approval.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterGroupUserCreateDto {

    private Integer masterGroupUserId;

    @NotNull(message = "Master group user code is mandatory")
    @Size(max = 255, message = "Master group user name must not exceed 255 characters")
    private String masterGroupUserCode;

    @NotNull(message = "Master group user code is mandatory")
    @Size(max = 255, message = "Master group user name must not exceed 255 characters")
    private String masterGroupUserName;

    @NotNull(message = "Master group user role is mandatory")
    @Size(max = 255, message = "Master group role name must not exceed 255 characters")
    private String masterGroupUserRole;

    @NotNull(message = "Master group user order is mandatory")
    @PositiveOrZero(message = "orderIndex must be 0 or positive")
    private Byte masterGroupUserOrder;

    @Size(max = 255, message = "Master group user description must not exceed 255 characters")
    private String masterGroupUserDescription;

    @NotNull(message = "You must specify whether this menu is a parent or not")
    private Integer userId;


}
