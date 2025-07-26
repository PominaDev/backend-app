package com.pomina.erpapp.appbaohanh.web.user_managerment.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.user_managerment.converter.SysUserRoleWebConverter;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.request.SysUserRoleWebRequest;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.erpapp.appbaohanh.web.user_managerment.entity.SysUserRoleWeb;
import com.pomina.erpapp.appbaohanh.web.user_managerment.mapper.SysUserRoleWebMapper;
import com.pomina.erpapp.appbaohanh.web.user_managerment.service.SysUerRoleWebService;
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
    public int create(SysUserRoleWebRequest dto) {
        return 0;
    }

    @Override
    public int update(Integer id, SysUserRoleWebRequest dto) {
        return 0;
    }

    @Override
    public SysUserRoleWebResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public PageResponse<SysUserRoleWebResponseDto> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<SysUserRoleWebResponseDto> getAllUserRoleWeb() {
        List<SysUserRoleWeb> sysUserRoleWebList = sysUserInWebMapper.findAll();
        return sysUserInWebConverter.toResponseList(sysUserRoleWebList);
    }
}
