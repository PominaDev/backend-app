package com.pomina.webapp.user_managerment.service.impl;

import com.pomina.webapp.user_managerment.converter.SysUserRoleWebConverter;
import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.webapp.user_managerment.entity.SysUserRoleWeb;
import com.pomina.webapp.user_managerment.mapper.SysUserRoleWebMapper;
import com.pomina.webapp.user_managerment.service.SysUerRoleWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserRoleWebServiceImpl implements SysUerRoleWebService {
    // Dependency injection
    private final SysUserRoleWebConverter sysUserInWebConverter;
    private final SysUserRoleWebMapper sysUserInWebMapper;

    @Override
    public List<SysUserRoleWebResponseDto> getAll() {
        List<SysUserRoleWeb> sysUserRoleWebList = sysUserInWebMapper.findAll();
        return sysUserInWebConverter.toResponseList(sysUserRoleWebList);
    }
}
