package com.pomina.webapp.statistic_user_active.by_u_location.mapper;

import com.pomina.webapp.statistic_user_active.by_u_location.entity.StatisticsSysUserActiveByULocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsSysUserActiveByULocationMapper {
    List<StatisticsSysUserActiveByULocation> findAllUserActiveULocation();
}
