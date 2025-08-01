package com.pomina.webapp.user_managerment.entity;

import com.pomina.common.model.BaseEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String userDescription;
    private Integer roleId;
    private Boolean isActive;
    private Integer oldId;

    private String roleName;
    private String roleDescription;

    private Integer locationId;
    private Double latitude;
    private Double longitude;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;
}
