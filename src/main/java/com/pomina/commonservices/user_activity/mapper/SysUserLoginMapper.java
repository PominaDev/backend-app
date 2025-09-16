package com.pomina.commonservices.user_activity.mapper;

import com.pomina.commonservices.user_activity.entity.SysUserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SysUserLoginMapper {

    int insert(SysUserLogin sysUserLogin);

    int updateLogoutTime(@Param("userId") int userId,
                         @Param("action") int action,
                         @Param("logoutTime") LocalDateTime logoutTime);

    List<SysUserLogin> findByUserId(@Param("userId") Integer userId);
}
