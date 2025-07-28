package com.pomina.servicecommon.location.service;

import com.pomina.common.service.BaseService;
import com.pomina.servicecommon.location.dto.request.LocationRequestDto;
import com.pomina.servicecommon.location.dto.response.CheckLocationResponse;
import com.pomina.servicecommon.location.dto.response.LocationResponseDto;

public interface LocationService extends BaseService<LocationRequestDto, LocationResponseDto, Long> {
    LocationResponseDto registerLocation(LocationRequestDto request);

    CheckLocationResponse checkLocation(LocationRequestDto request);
}
