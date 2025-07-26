package com.pomina.erpapp.appbaohanh.web.menu_permission.mapper;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterMenuMapper {
    int insert(@Param("list") List<MasterMenu> masterMenuList);
    int update(@Param("list") List<MasterMenu> masterMenuList);
    int softDelete(@Param("list") List<Integer> ids);
    MasterMenu findById(@Param("id") Integer id);
    boolean existsById(@Param("id") Integer id);
    int countAll();
    List<MasterMenu> findAll();
    List<MasterMenu> findAllPaged(@Param("offset") int offset,
                         @Param("limit") int limit,
                         @Param("paging") PageRequest pageRequest);



}
