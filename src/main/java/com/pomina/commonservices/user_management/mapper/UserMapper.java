package com.pomina.commonservices.user_management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.user_management.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
