package com.pomina.commonservices.user_management.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.user_management.dto.request.RoleRequestDto;
import com.pomina.commonservices.user_management.dto.response.RoleResponseDto;
import com.pomina.commonservices.user_management.entity.SysRole;

import java.util.List;

public interface RoleService extends BaseService<RoleRequestDto, RoleResponseDto, Integer> {
    List<RoleResponseDto> getAll();

    List<SysRole> findByRoleNames(List<String> roleName);
}
