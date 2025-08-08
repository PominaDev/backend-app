package com.pomina.webapp.statistic_user_active.by_u_location.service.impl;

import com.pomina.webapp.statistic_user_active.by_u_location.converter.StatisticsSysUserActiveByULocationConverter;
import com.pomina.webapp.statistic_user_active.by_u_location.dto.respone.StatisticsSysUserActiveByULocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_u_location.entity.StatisticsSysUserActiveByULocation;
import com.pomina.webapp.statistic_user_active.by_u_location.mapper.StatisticsSysUserActiveByULocationMapper;
import com.pomina.webapp.statistic_user_active.by_u_location.service.StatisticsSysUserActiveByULocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsSysUserActiveByULocationServiceImpl implements StatisticsSysUserActiveByULocationService {
    // Dependency injection
    private final StatisticsSysUserActiveByULocationConverter statisticsSysUserActiveByULocationConverter;
    private final StatisticsSysUserActiveByULocationMapper statisticsSysUserActiveByULocationMapper;

    @Override
    public List<StatisticsSysUserActiveByULocationResponseDto> getAllUserActiveULocation() {
        List<StatisticsSysUserActiveByULocation> statisticsSysUserActiveByULocationList = statisticsSysUserActiveByULocationMapper.findAllUserActiveULocation();
        return statisticsSysUserActiveByULocationConverter.toResponseList(statisticsSysUserActiveByULocationList);
    }
}
