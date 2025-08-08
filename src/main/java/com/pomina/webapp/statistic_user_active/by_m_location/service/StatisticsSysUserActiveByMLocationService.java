package com.pomina.webapp.statistic_user_active.by_m_location.service;

import com.pomina.webapp.statistic_user_active.by_m_location.dto.respone.StatisticsSysUserActiveByMLocationResponseDto;

import java.util.List;

public interface StatisticsSysUserActiveByMLocationService {
    List<StatisticsSysUserActiveByMLocationResponseDto> getAllUserActiveMLocation();
}
