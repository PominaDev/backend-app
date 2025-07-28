package com.pomina.webapp.user_managerment.mapper;

import com.pomina.webapp.user_managerment.entity.SysUserRoleWeb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleWebMapper {
    List<SysUserRoleWeb> findAllUserActiveRoleWeb();
}
