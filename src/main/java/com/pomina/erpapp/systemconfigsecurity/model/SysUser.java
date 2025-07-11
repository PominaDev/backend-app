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

    private Long userId;

    private String username;

    private String phoneNumber;

    private String password;

    private String hoVaTen;

    private String maSoThue;

    private String diaChi1;

    private String diaChi2;

    private String diaChi3;

    private String diaChi4;

    private String diaChi5;

    private String city;

    private String locationCode;

    private String bankName;

    private String bankNumber;

    private String description;

    private int roleId;

    private int isActive;

    // JOIN
    private String roleName;
}

