package com.pomina.commonservices.user_activity.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLogin extends BaseEntity {

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
}
