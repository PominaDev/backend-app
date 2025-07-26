package com.pomina.erpapp.appbaohanh.web.user_managerment.service;

import com.pomina.erpapp.appbaohanh.common.service.BaseService;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.request.SysUserRoleWebRequest;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.respone.SysUserRoleWebResponseDto;

import java.util.List;

public interface SysUerRoleWebService extends BaseService<SysUserRoleWebRequest, SysUserRoleWebResponseDto, Integer> {
    List<SysUserRoleWebResponseDto> getAllUserRoleWeb();
}
