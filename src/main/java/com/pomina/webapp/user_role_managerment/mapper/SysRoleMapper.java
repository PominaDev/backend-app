package com.pomina.webapp.user_role_managerment.mapper;


import com.pomina.webapp.user_role_managerment.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> findAll();
}
