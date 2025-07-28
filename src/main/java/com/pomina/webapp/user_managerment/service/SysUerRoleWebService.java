package com.pomina.webapp.user_managerment.service;

import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleWebResponseDto;

import java.util.List;

public interface SysUerRoleWebService {
    List<SysUserRoleWebResponseDto> getAllUserActiveRoleWeb();
}
