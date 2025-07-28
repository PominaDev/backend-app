package com.pomina.appbaohanh.security.mapper;

import com.pomina.appbaohanh.security.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    SysUser getUserLogin(@Param("username") String username);

    SysUser findByUserName(@Param("username") String username);

    SysUser findByUserId(@Param("userId") Integer userId);

    int insert(SysUser sysUser);
}
