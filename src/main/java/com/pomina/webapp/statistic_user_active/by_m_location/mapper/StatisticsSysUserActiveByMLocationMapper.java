package com.pomina.webapp.statistic_user_active.by_m_location.mapper;

import com.pomina.webapp.statistic_user_active.by_m_location.entity.StatisticsSysUserActiveByMLocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsSysUserActiveByMLocationMapper {
    List<StatisticsSysUserActiveByMLocation> findAllUserActiveMLocation();
}
