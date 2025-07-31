package com.pomina.webapp.user_role_managerment.service;

import com.pomina.webapp.user_role_managerment.dto.SysRoleDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;

import java.util.List;

public interface SysUserRoleManagermentService {
    List<SysRoleDto> findAll();
}
