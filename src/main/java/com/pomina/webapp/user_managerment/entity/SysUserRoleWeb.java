package com.pomina.webapp.user_managerment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleWeb {
    private Integer userId;
    private String username;
    private String phoneNumber;
}
