package com.pomina.erpapp.appbaohanh.web.menu_permission.service;

import com.pomina.erpapp.appbaohanh.common.service.BaseService;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterPermissionResponseDto;

import java.util.List;

public interface MasterPermissionService extends BaseService<MasterPermissionRequestDto, MasterPermissionResponseDto, Integer> {

    Integer createListPermisstion(List<MasterPermissionRequestDto> requestDtoList);
    Integer updateListPermisstion(List<MasterPermissionRequestDto> requestDtoList);

}
