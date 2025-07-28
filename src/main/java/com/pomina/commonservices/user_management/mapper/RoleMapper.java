package com.pomina.commonservices.user.management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.user.management.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
}
