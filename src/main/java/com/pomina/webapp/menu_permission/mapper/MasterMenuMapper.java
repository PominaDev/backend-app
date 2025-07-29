package com.pomina.webapp.menu_permission.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.menu_permission.entity.MasterMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterMenuMapper extends BaseMapper<MasterMenu> {
    MasterMenu findByName(@Param("masterMenuName") String name);
    Integer softDeleteList(@Param("list") List<Integer> idList);
}
