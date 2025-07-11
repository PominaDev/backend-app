package com.pomina.erpapp.systemconfigsecurity.mapper;

import com.pomina.erpapp.systemconfigsecurity.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    SysUser getUserLogin(@Param("username") String username);
}
