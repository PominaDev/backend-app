package com.pomina.webapp.statistic_user_active.by_role.service;

import com.pomina.webapp.statistic_user_active.by_role.dto.respone.StatisticsSysUserActiveByRoleResponseDto;

import java.util.List;

public interface StatisticsSysUserActiveByRoleService {
    List<StatisticsSysUserActiveByRoleResponseDto> getAllUserActiveRole();
}
