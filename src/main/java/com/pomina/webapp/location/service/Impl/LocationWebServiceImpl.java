package com.pomina.webapp.location.service.Impl;

import com.pomina.webapp.location.converter.LocationWebConverter;
import com.pomina.webapp.location.dto.request.LocationRequestCreateDto;
import com.pomina.webapp.location.dto.request.LocationRequestUpdateDto;
import com.pomina.webapp.location.entity.Location;
import com.pomina.webapp.location.mapper.LocationWebMapper;
import com.pomina.webapp.location.service.LocationWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationWebServiceImpl implements LocationWebService {
    private final LocationWebMapper locationWebMapper;
    private final LocationWebConverter locationWebConverter;

    @Override
    public int updateLocation(Integer userId, LocationRequestUpdateDto locationRequestUpdateDto) {
        locationRequestUpdateDto.setUserId(userId);
        locationRequestUpdateDto.setUpdatedAt(LocalDateTime.now());
        Location location = locationWebConverter.toLocation(locationRequestUpdateDto);
        return locationWebMapper.update(location);
    }

    @Override
    public int createLocation(LocationRequestCreateDto locationRequestCreateDto) {
        Location location = locationWebConverter.toLocation(locationRequestCreateDto);
        return locationWebMapper.insert(location);
    }
}
