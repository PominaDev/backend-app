package com.pomina.webapp.user_managerment.entity;

import com.pomina.common.model.BaseEntity;
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
    private Integer isActive;
    private Integer oldId;
}
