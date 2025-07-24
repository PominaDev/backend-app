package com.pomina.erpapp.systemconfigsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Integer userId;

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

    private Boolean isActive;

    private String oldId;

    // JOIN
    private String roleName;
}

