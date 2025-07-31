package com.pomina.webapp.location.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.location.dto.request.LocationRequestCreateDto;
import com.pomina.webapp.location.dto.request.LocationRequestDto;
import com.pomina.webapp.location.dto.request.LocationRequestUpdateDto;
import com.pomina.webapp.location.dto.respones.LocationResponseDto;
import com.pomina.webapp.location.entity.Location;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface LocationWebConverter extends BaseConverter<LocationRequestDto, LocationResponseDto, Location> {
    Location toLocation(LocationRequestUpdateDto locationRequestUpdateDto);

    Location toLocation(LocationRequestCreateDto locationRequestCreateDto);
}
