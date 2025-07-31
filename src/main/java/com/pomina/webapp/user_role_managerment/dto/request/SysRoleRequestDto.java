package com.pomina.webapp.user_role_managerment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoleRequestDto {
    private Integer id;
    private String roleName;
    private String description;
}
