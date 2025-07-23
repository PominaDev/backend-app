package com.pomina.erpapp.appbaohanh.location_validation.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationRequestDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.LocationResponseDto;
import com.pomina.erpapp.appbaohanh.location_validation.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationConverter extends BaseConverter<LocationRequestDto, LocationResponseDto, Location> {
}
