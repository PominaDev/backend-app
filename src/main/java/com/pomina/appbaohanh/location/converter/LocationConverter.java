package com.pomina.appbaohanh.location.converter;

import com.pomina.appbaohanh.common.converter.BaseConverter;
import com.pomina.appbaohanh.location.dto.request.LocationRequestDto;
import com.pomina.appbaohanh.location.dto.response.LocationResponseDto;
import com.pomina.appbaohanh.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationConverter extends BaseConverter<LocationRequestDto, LocationResponseDto, Location> {
}
