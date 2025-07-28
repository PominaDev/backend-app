package com.pomina.appbaohanh.user_managerment.entity;

import com.pomina.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {

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

    // Role
    private String roleName;

    // Location
    private Integer locationId;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;
}
