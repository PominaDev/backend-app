package com.pomina.appbaohanh.menu_permission.mapper;

import com.pomina.appbaohanh.common.mapper.BaseMapper;
import com.pomina.appbaohanh.menu_permission.entity.MasterMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MasterMenuMapper extends BaseMapper<MasterMenu> {
    MasterMenu findByName(@Param("masterMenuName") String name);
}
