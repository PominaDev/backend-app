package com.pomina.webapp.user_managerment.service.impl;

import com.pomina.webapp.user_managerment.converter.SysUserRoleMobileConverter;
import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleMobileResponseDto;
import com.pomina.webapp.user_managerment.entity.SysUserRoleMobile;
import com.pomina.webapp.user_managerment.mapper.SysUserRoleMobileMapper;
import com.pomina.webapp.user_managerment.service.SysUserRoleMobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserRoleMobileServiceImpl implements SysUserRoleMobileService {

    private final SysUserRoleMobileConverter sysUserInMobileConverter;
    private final SysUserRoleMobileMapper sysUserInMobileMapper;

    @Override
    public List<SysUserRoleMobileResponseDto> getAllUserActiveRoleMobile() {
        List<SysUserRoleMobile> sysUserRoleMobileList = sysUserInMobileMapper.findAllUserActiveRoleMobile();
        return sysUserInMobileConverter.toResponseList(sysUserRoleMobileList);
    }
}
