package com.pomina.app.menu_permission.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.app.menu_permission.entity.MasterMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MasterMenuMapper extends BaseMapper<MasterMenu> {
    MasterMenu findByName(@Param("masterMenuName") String name);
}
