package com.pomina.erpapp.appbaohanh.web.menu_permission.mapper;

import com.pomina.erpapp.appbaohanh.common.mapper.BaseMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterPermissionMapper extends BaseMapper<MasterPermission> {

    /**
     * Find all permissions with menu details by user ID
     * 
     * @param sysUserId the user ID
     * @return list of permissions with menu and user details
     */
    List<MasterPermission> findPermissionsWithMenuByUserId(@Param("sysUserId") Integer sysUserId);

    /**
     * Find user permissions by user ID (existing method)
     * 
     * @param sysUserId the user ID
     * @return list of permissions
     */
    List<MasterPermission> findBySysUserId(@Param("sysUserId") Integer sysUserId);
}
