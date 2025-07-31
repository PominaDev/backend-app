package com.pomina.webapp.user_managerment.dto.respone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserResponeDto {
    private String username;
    private String password;
    private String hoVaTen;
    private String phoneNumber;
    private String maSoThue;
    private String masterLocationCode;
    private String bankName;
    private String bankNumber;
    private String description;
    private Integer roleId;
    private Integer isActive;
    private Integer oldId;
    private String isDeleted;

    // JOIN sys_role
    private String roleName;

    // JOIN u_location
    private Integer locationId;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;
}
