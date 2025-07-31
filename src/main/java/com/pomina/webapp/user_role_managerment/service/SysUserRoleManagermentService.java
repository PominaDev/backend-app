package com.pomina.webapp.user_role_managerment.service;

import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;

import java.util.List;

public interface SysUserRoleManagermentService {
    List<SysRoleResponeDto> findAll();
}
