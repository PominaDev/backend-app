package com.pomina.erpapp.appbaohanh.location_validation.service;

import com.pomina.erpapp.appbaohanh.common.service.BaseService;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationRequestDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.CheckLocationResponse;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.LocationResponseDto;

public interface LocationService extends BaseService<LocationRequestDto, LocationResponseDto, Long> {
    LocationResponseDto registerLocation(LocationRequestDto request);

    CheckLocationResponse checkLocation(LocationRequestDto request);
}
