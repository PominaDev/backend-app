package com.pomina.webapp.user_role_managerment.service.impl;

import com.pomina.webapp.user_role_managerment.converter.SysRoleConverter;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import com.pomina.webapp.user_role_managerment.mapper.SysRoleMapper;
import com.pomina.webapp.user_role_managerment.service.SysUserRoleManagermentService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserRoleManagermentServiceImpl implements SysUserRoleManagermentService {

    private final SysRoleMapper sysRoleMapper;

    private final SysRoleConverter sysRoleConverter;

    @Override
    public List<SysRoleResponeDto> findAll() {

        List<SysRole> sysRoleList = sysRoleMapper.findAll();
        if (sysRoleList != null) {
            return sysRoleConverter.toResponseList(sysRoleList);
        }

        return Collections.emptyList();
    }
}
