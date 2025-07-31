package com.pomina.webapp.user_role_managerment.service.Impl;

import com.pomina.webapp.user_role_managerment.converter.SysRoleConverter;
import com.pomina.webapp.user_role_managerment.dto.SysRoleDto;
import com.pomina.webapp.user_role_managerment.mapper.SysRoleMapper;
import com.pomina.webapp.user_role_managerment.service.SysUserRoleManagermentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserRoleManagermentServiceImpl implements SysUserRoleManagermentService {
    private final SysRoleMapper sysRoleMapper;
    private final SysRoleConverter sysRoleConverter;

    @Override
    public List<SysRoleDto> findAll() {
        return sysRoleConverter.toResponseList(sysRoleMapper.findAll());
    }
}
