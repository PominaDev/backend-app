package com.pomina.webapp.user_managerment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserRequestDto {
    private Integer userId;

    @NotBlank(message = "Username must not be empty")
    @Size(max = 255, message = "Username must not exceed 255 characters")
    private String username;

    @NotBlank(message = "Password must not be empty")
    @Size(max = 255, message = "Password must not exceed 255 characters")
    private String password;

    private String hoVaTen;

    private String phoneNumber;

    private String maSoThue;

    @NotBlank(message = "Master location code must not be empty")
    private String masterLocationCode;

    private String bankName;

    private String bankNumber;

    private String description;

    @NotNull(message = "Role ID must not be null")
    private Integer roleId;

    private Integer isActive;

    private Integer oldId;

    @NotBlank(message = "User create must not be empty")
    @Size(max = 255, message = "Username must not exceed 255 characters")
    private String createBy;

    @NotBlank(message = "User update must not be empty")
    @Size(max = 255, message = "Username must not exceed 255 characters")
    private String updateBy;
}
