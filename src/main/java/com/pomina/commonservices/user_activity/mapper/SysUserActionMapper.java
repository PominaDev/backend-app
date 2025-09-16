package com.pomina.commonservices.user_activity.mapper;

import com.pomina.commonservices.user_activity.entity.SysUserAction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserActionMapper {

    int insert(SysUserAction action);

    List<SysUserAction> findRecentByUser(@Param("userId") Integer userId,
                                         @Param("limit") int limit);
}
