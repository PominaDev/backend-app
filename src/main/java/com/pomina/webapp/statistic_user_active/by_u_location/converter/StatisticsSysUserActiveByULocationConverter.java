package com.pomina.webapp.statistic_user_active.by_u_location.converter;

import com.pomina.webapp.statistic_user_active.by_u_location.dto.respone.StatisticsSysUserActiveByULocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_u_location.entity.StatisticsSysUserActiveByULocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatisticsSysUserActiveByULocationConverter {
    // Mapping from List<entity> to List<ResponseDto>
    List<StatisticsSysUserActiveByULocationResponseDto> toResponseList(List<StatisticsSysUserActiveByULocation> entityList);
}
