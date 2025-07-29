package com.pomina.commonservices.location.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.CheckLocationResponse;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;

public interface LocationService extends BaseService<LocationRequestDto, LocationResponseDto, Long> {
    LocationResponseDto registerLocation(LocationRequestDto request);

    CheckLocationResponse checkLocation(LocationRequestDto request);

    LocationResponseDto getLocationFromCoordinates(Double latitude, Double longitude);
}
