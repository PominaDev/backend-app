package com.pomina.erpapp.appbaohanh.web.menu_permission.mapper;

import com.pomina.erpapp.appbaohanh.common.mapper.BaseMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MasterMenuMapper extends BaseMapper<MasterMenu> {
    MasterMenu findByName(@Param("masterMenuName") String name);
}
