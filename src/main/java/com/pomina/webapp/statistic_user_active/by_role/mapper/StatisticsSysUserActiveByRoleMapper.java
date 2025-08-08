package com.pomina.webapp.statistic_user_active.by_role.mapper;

import com.pomina.webapp.statistic_user_active.by_role.entity.StatisticsSysUserActiveByRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsSysUserActiveByRoleMapper {
    List<StatisticsSysUserActiveByRole> findAllUserActiveRole();
}
