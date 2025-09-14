package com.pomina.webapp.sys_user_login.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLogin {
    private Integer id;
    private Integer userId;
    private String username;
    private String fullName;
    private String token;
    private Integer action;
    private Integer result;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private String status;
    private String noted;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private Boolean isDeleted;
}
