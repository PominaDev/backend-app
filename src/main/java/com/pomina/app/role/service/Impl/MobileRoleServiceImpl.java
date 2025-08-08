package com.pomina.app.role.service.Impl;

import com.pomina.app.role.converter.MobileRoleConverter;
import com.pomina.app.role.dto.response.MobileRoleResponeseDto;
import com.pomina.app.role.service.MobileRoleService;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import com.pomina.webapp.user_role_managerment.service.impl.SysUserRoleManagermentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MobileRoleServiceImpl implements MobileRoleService {
    private final SysUserRoleManagermentServiceImpl sysUserRoleManagermentService;
    private final MobileRoleConverter mobileRoleConverter;

    /**
     * Retrieves all mobile roles.
     *
     * @return A list of {@link MobileRoleResponeseDto} representing the mobile roles.
     */
    @Override
    public List<MobileRoleResponeseDto> findAll() {
        List<SysRoleResponeDto> sysUserRoleManagermentServiceAll = sysUserRoleManagermentService.findAll();
        return mobileRoleConverter.toMobileRoleResponeDto(sysUserRoleManagermentServiceAll);
    }
}
