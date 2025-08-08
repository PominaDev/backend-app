package com.pomina.app.role.service;

import com.pomina.app.role.dto.response.MobileRoleResponeseDto;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;

import java.util.List;

public interface MobileRoleService {
    List<MobileRoleResponeseDto> findAll();
}
