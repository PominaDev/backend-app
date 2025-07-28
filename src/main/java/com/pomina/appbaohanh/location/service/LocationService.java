package com.pomina.appbaohanh.location.service;

import com.pomina.appbaohanh.common.service.BaseService;
import com.pomina.appbaohanh.location.dto.request.LocationRequestDto;
import com.pomina.appbaohanh.location.dto.response.CheckLocationResponse;
import com.pomina.appbaohanh.location.dto.response.LocationResponseDto;

public interface LocationService extends BaseService<LocationRequestDto, LocationResponseDto, Long> {
    LocationResponseDto registerLocation(LocationRequestDto request);

    CheckLocationResponse checkLocation(LocationRequestDto request);
}
