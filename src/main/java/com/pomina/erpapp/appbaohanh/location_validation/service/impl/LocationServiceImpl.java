package com.pomina.erpapp.appbaohanh.location_validation.service.impl;

import com.pomina.erpapp.appbaohanh.common.config.datasource.CustomDataSource;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.erpapp.appbaohanh.common.exception.AppException;
import com.pomina.erpapp.appbaohanh.common.exception.ErrorCode;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.location_validation.converter.LocationConverter;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationRequestDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.CheckLocationResponse;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.LocationResponseDto;
import com.pomina.erpapp.appbaohanh.location_validation.entity.Location;
import com.pomina.erpapp.appbaohanh.location_validation.mapper.LocationMapper;
import com.pomina.erpapp.appbaohanh.external.nominatim.client.NominatimClient;
import com.pomina.erpapp.appbaohanh.external.nominatim.model.NominatimResponse;
import com.pomina.erpapp.appbaohanh.location_validation.service.LocationService;
import com.pomina.erpapp.appbaohanh.location_validation.util.Haversine;
import com.pomina.erpapp.systemconfigsecurity.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final NominatimClient nominatimClient;

    private final LocationMapper locationMapper;

    private final LocationConverter locationConverter;

    @Override
    public int create(LocationRequestDto dto) {
        return 0;
    }

    @Override
    public int update(Long id, LocationRequestDto dto) {
        return 0;
    }

    @Override
    public LocationResponseDto getById(Long id) {
        return null;
    }

    @Override
    public PageResponse<LocationResponseDto> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public LocationResponseDto registerLocation(LocationRequestDto request) {

        if (request == null) {
            return null;
        }

        // Call Nominatim API to get address details
        NominatimResponse nominatimResponse = nominatimClient.reverseGeocode(
                request.getLatitude(), request.getLongitude());

        // Map to Location entity
        Location location = new Location();
        location.setUserId(request.getUserId());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setRadius(request.getRadius());
        if (nominatimResponse.getAddress() != null) {
            location.setRoad(nominatimResponse.getAddress().getRoad());
            location.setCity(nominatimResponse.getAddress().getCity());
            location.setCountry(nominatimResponse.getAddress().getCountry());
            location.setCountryCode(nominatimResponse.getAddress().getCountryCode());
        }

        // Save to database
        locationMapper.insert(location);

        // Map to response
        LocationResponseDto locationResponse = new LocationResponseDto();
        locationResponse.setUserId(location.getUserId());
        locationResponse.setLatitude(location.getLatitude());
        locationResponse.setLongitude(location.getLongitude());
        locationResponse.setRoad(location.getRoad());
        locationResponse.setCity(location.getCity());
        locationResponse.setCountry(location.getCountry());
        locationResponse.setCountryCode(location.getCountryCode());

        return locationResponse;
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public CheckLocationResponse checkLocation(LocationRequestDto request) {

        if (request == null) {
            return null;
        }

        // Nếu không truyền thì lấy từ context
        Long userId = request.getUserId();
        if (userId == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            userId = principal.getUserId();
            if (userId == null) {
                throw new AppException(ErrorCode.USER_NOT_FOUND);
            }
        }

        // Get registered location
        Location registeredLocation = locationMapper.findByUserId(userId);
        if (registeredLocation == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        // Calculate distance
        double distance = Haversine.calculateDistance(
                request.getLatitude(), request.getLongitude(),
                registeredLocation.getLatitude(), registeredLocation.getLongitude());

        boolean isWithinLocation = distance <= registeredLocation.getRadius();

        LocationResponseDto locationResponse = new LocationResponseDto();
        locationResponse.setUserId(registeredLocation.getUserId());
        locationResponse.setLatitude(registeredLocation.getLatitude());
        locationResponse.setLongitude(registeredLocation.getLongitude());
        locationResponse.setRoad(registeredLocation.getRoad());
        locationResponse.setCity(registeredLocation.getCity());
        locationResponse.setCountry(registeredLocation.getCountry());
        locationResponse.setCountryCode(registeredLocation.getCountryCode());

        CheckLocationResponse response = new CheckLocationResponse();
        response.setIsWithinLocation(isWithinLocation);
        response.setDistance(distance);
        response.setRegisteredLocation(locationResponse);

        return response;
    }
}
