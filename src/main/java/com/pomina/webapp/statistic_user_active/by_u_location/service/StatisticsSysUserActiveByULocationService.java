package com.pomina.webapp.statistic_user_active.by_u_location.service;

import com.pomina.webapp.statistic_user_active.by_u_location.dto.respone.StatisticsSysUserActiveByULocationResponseDto;

import java.util.List;

public interface StatisticsSysUserActiveByULocationService {
    List<StatisticsSysUserActiveByULocationResponseDto> getAllUserActiveULocation();
}
