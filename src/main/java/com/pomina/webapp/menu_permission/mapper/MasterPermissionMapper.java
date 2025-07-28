package com.pomina.app.menu_permission.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.app.menu_permission.entity.MasterPermission;
import com.pomina.app.menu_permission.entity.MenuPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterPermissionMapper extends BaseMapper<MasterPermission> {
    List<MenuPermission> findMenuPermissionByUserId(@Param("userId") Integer userId);
}
