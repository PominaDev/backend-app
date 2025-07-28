package com.pomina.commonservices.location.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationConverter extends BaseConverter<LocationRequestDto, LocationResponseDto, Location> {
}
