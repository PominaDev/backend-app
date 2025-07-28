package com.pomina.servicecommon.location.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.servicecommon.location.dto.request.LocationRequestDto;
import com.pomina.servicecommon.location.dto.response.LocationResponseDto;
import com.pomina.servicecommon.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationConverter extends BaseConverter<LocationRequestDto, LocationResponseDto, Location> {
}
