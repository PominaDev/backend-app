package com.pomina.webapp.statistic_user_active.by_m_location.converter;

import com.pomina.webapp.statistic_user_active.by_m_location.dto.respone.StatisticsSysUserActiveByMLocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_m_location.entity.StatisticsSysUserActiveByMLocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatisticsSysUserActiveByMLocationConverter {
    // Mapping from List<entity> to List<ResponseDto>
    List<StatisticsSysUserActiveByMLocationResponseDto> toResponseList(List<StatisticsSysUserActiveByMLocation> entityList);
}
