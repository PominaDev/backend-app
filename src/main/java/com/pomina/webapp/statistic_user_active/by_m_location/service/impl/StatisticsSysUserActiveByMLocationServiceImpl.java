package com.pomina.webapp.statistic_user_active.by_m_location.service.impl;

import com.pomina.webapp.statistic_user_active.by_m_location.converter.StatisticsSysUserActiveByMLocationConverter;
import com.pomina.webapp.statistic_user_active.by_m_location.dto.respone.StatisticsSysUserActiveByMLocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_m_location.entity.StatisticsSysUserActiveByMLocation;
import com.pomina.webapp.statistic_user_active.by_m_location.mapper.StatisticsSysUserActiveByMLocationMapper;
import com.pomina.webapp.statistic_user_active.by_m_location.service.StatisticsSysUserActiveByMLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsSysUserActiveByMLocationServiceImpl implements StatisticsSysUserActiveByMLocationService {
    // Dependency injection
    private final StatisticsSysUserActiveByMLocationConverter statisticsSysUserActiveByMLocationConverter;
    private final StatisticsSysUserActiveByMLocationMapper statisticsSysUserActiveByMLocationMapper;

    @Override
    public List<StatisticsSysUserActiveByMLocationResponseDto> getAllUserActiveMLocation() {
        List<StatisticsSysUserActiveByMLocation> statisticsSysUserActiveByMLocationList = statisticsSysUserActiveByMLocationMapper.findAllUserActiveMLocation();
        return statisticsSysUserActiveByMLocationConverter.toResponseList(statisticsSysUserActiveByMLocationList);
    }
}
