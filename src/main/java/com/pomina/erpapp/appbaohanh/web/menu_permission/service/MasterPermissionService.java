package com.pomina.erpapp.appbaohanh.web.menu_permission.service;

import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.UserMenuPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterPermission;

import java.util.List;

public interface MasterPermissionService {

    /**
     * Get user menu permissions organized as tree structure
     * 
     * @param userId the user ID
     * @return user menu permission response DTO
     */
    UserMenuPermissionResponseDto getUserMenuPermissions(Integer userId);

    /**
     * DEBUG: Get raw permissions for testing
     * 
     * @param userId the user ID
     * @return raw permission list
     */
    List<MasterPermission> getDebugPermissions(Integer userId);
}
