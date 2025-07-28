package com.pomina.appbaohanh.menu_permission.mapper;

import com.pomina.appbaohanh.common.mapper.BaseMapper;
import com.pomina.appbaohanh.menu_permission.entity.MasterPermission;
import com.pomina.appbaohanh.menu_permission.entity.MenuPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterPermissionMapper extends BaseMapper<MasterPermission> {
    List<MenuPermission> findMenuPermissionByUserId(@Param("userId") Integer userId);
}
