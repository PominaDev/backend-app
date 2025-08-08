package com.pomina.webapp.statistic_user_active.by_role.service.impl;

import com.pomina.webapp.statistic_user_active.by_role.converter.StatisticsSysUserActiveByRoleConverter;
import com.pomina.webapp.statistic_user_active.by_role.dto.respone.StatisticsSysUserActiveByRoleResponseDto;
import com.pomina.webapp.statistic_user_active.by_role.entity.StatisticsSysUserActiveByRole;
import com.pomina.webapp.statistic_user_active.by_role.mapper.StatisticsSysUserActiveByRoleMapper;
import com.pomina.webapp.statistic_user_active.by_role.service.StatisticsSysUserActiveByRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsSysUserActiveByRoleServiceImpl implements StatisticsSysUserActiveByRoleService {
    // Dependency injection
    private final StatisticsSysUserActiveByRoleConverter statisticsSysUserActiveByRoleConverter;
    private final StatisticsSysUserActiveByRoleMapper statisticsSysUserActiveByRoleMapper;

    @Override
    public List<StatisticsSysUserActiveByRoleResponseDto> getAllUserActiveRole() {
        List<StatisticsSysUserActiveByRole> statisticsSysUserActiveByRoleList = statisticsSysUserActiveByRoleMapper.findAllUserActiveRole();
        return statisticsSysUserActiveByRoleConverter.toResponseList(statisticsSysUserActiveByRoleList);
    }
}
