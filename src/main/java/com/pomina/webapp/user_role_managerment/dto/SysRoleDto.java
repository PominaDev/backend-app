package com.pomina.webapp.user_role_managerment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleDto {
    private Integer id;
    private String roleName;
    private String description;
}
