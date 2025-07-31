package com.pomina.webapp.user_role_managerment.service;

import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleRespone;

import java.util.List;

public interface SysUserRoleManagermentService {
    List<SysRoleRespone> findAll();
}
