package com.pomina.webapp.user_managerment.service;

import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleMobileResponseDto;
import java.util.List;

public interface SysUserRoleMobileService {
    List<SysUserRoleMobileResponseDto> getAllUserActiveRoleMobile();
}
