package com.pomina.webapp.user_role_managerment.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends BaseEntity {
    private Integer id;
    private String roleName;
    private String description;
}
