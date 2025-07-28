package com.pomina.appbaohanh.user_managerment.mapper;

import com.pomina.appbaohanh.common.mapper.BaseMapper;
import com.pomina.appbaohanh.user_managerment.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
