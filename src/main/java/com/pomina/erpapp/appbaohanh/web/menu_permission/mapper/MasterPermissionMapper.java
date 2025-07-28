package com.pomina.erpapp.appbaohanh.web.menu_permission.mapper;

import com.pomina.erpapp.appbaohanh.common.mapper.BaseMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterPermission;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MenuPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterPermissionMapper extends BaseMapper<MasterPermission> {
    List<MenuPermission> findMenuPermissionByUserId(@Param("userId") Integer userId);
}
