package com.pomina.webapp.statistic_user_active.by_role.converter;

import com.pomina.webapp.statistic_user_active.by_role.dto.respone.StatisticsSysUserActiveByRoleResponseDto;
import com.pomina.webapp.statistic_user_active.by_role.entity.StatisticsSysUserActiveByRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatisticsSysUserActiveByRoleConverter {
    // Mapping from List<entity> to List<ResponseDto>
    List<StatisticsSysUserActiveByRoleResponseDto> toResponseList(List<StatisticsSysUserActiveByRole> entityList);
}
