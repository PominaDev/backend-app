package com.pomina.commonservices.location.service.impl;

import com.pomina.common.config.datasources.CustomDataSource;
import com.pomina.common.config.datasources.DataSourceType;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.CheckLocationResponse;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.entity.Location;
import com.pomina.commonservices.location.external.google_geocoding.dto.LocationResponse;
import com.pomina.commonservices.location.external.google_geocoding.service.GoogleGeocodingService;
import com.pomina.commonservices.location.mapper.LocationMapper;
import com.pomina.commonservices.location.service.LocationService;
import com.pomina.commonservices.location.util.Haversine;
import com.pomina.security.config.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final GoogleGeocodingService googleGeocodingService;

    private final LocationMapper locationMapper;

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

        // 1. Reverse geocode
        LocationResponse locationResponse = googleGeocodingService.reverseGeocode(request.getLatitude(), request.getLongitude());

        // 2. Tạo entity từ request + locationResponse
        Location location = buildLocationEntity(request, locationResponse);

        // 3. Lưu DB
        locationMapper.insert(location);

        // 4. Trả DTO
        return buildLocationResponseDto(location, locationResponse);
    }

    private Location buildLocationEntity(LocationRequestDto request, LocationResponse locationResponse) {
        Location location = new Location();
        location.setUserId(request.getUserId());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setRadius(request.getRadius());

        if (locationResponse != null) {
            location.setAddress01(
                    joinNotNull(locationResponse.getStreetNumber(), locationResponse.getStreet())
            );
            location.setAddress02(locationResponse.getWard());
            location.setAddress03(locationResponse.getDistrict());
            location.setAddress04(locationResponse.getCity());
            location.setAddress05(locationResponse.getCountry());
            location.setFullAddress(locationResponse.getFullAddress());
            location.setCountryCode(locationResponse.getPostalCode());
        }

        return location;
    }

    private LocationResponseDto buildLocationResponseDto(Location location, LocationResponse locationResponse) {
        LocationResponseDto dto = new LocationResponseDto();
        dto.setUserId(location.getUserId());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setRoad(location.getAddress01());
        dto.setWard(location.getAddress02());
        dto.setDistrict(location.getAddress03());
        dto.setCity(location.getAddress04());
        dto.setCountry(location.getAddress05());
        dto.setFullAddress(locationResponse != null ? locationResponse.getFullAddress() : null);
        return dto;
    }

    private String joinNotNull(String part1, String part2) {
        if (part1 == null) return part2;
        if (part2 == null) return part1;
        return part1 + " " + part2;
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public CheckLocationResponse checkLocation(LocationRequestDto request) {
        if (request == null) {
            return null;
        }

        Integer userId = resolveUserId(request.getUserId());
        Location registeredLocation = fetchRegisteredLocation(userId);

        double distance = Haversine.calculateDistance(
                request.getLatitude(), request.getLongitude(),
                registeredLocation.getLatitude(), registeredLocation.getLongitude()
        );

        boolean isWithinLocation = distance <= registeredLocation.getRadius();

        return buildCheckLocationResponse(registeredLocation, distance, isWithinLocation);
    }

    // Test
    private Integer resolveUserId(Integer userId) {
        if (userId != null) {
            return userId;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal principal)) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        Integer resolvedId = principal.getUserId();

        if (resolvedId == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        return resolvedId;
    }

    private Location fetchRegisteredLocation(Integer userId) {
        Location location = locationMapper.findByUserId(userId);
        if (location == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return location;
    }

    private CheckLocationResponse buildCheckLocationResponse(Location location, double distance, boolean isWithin) {
        LocationResponseDto locationDto = new LocationResponseDto();
        locationDto.setUserId(location.getUserId());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        locationDto.setRoad(location.getAddress01());
        locationDto.setWard(location.getAddress02());
        locationDto.setDistrict(location.getAddress03());
        locationDto.setCity(location.getAddress04());
        locationDto.setCountry(location.getAddress05());
        locationDto.setFullAddress(location.getFullAddress());

        CheckLocationResponse response = new CheckLocationResponse();
        response.setIsWithinLocation(isWithin);
        response.setDistance(distance);
        response.setRegisteredLocation(locationDto);
        return response;
    }

    @Override
    public LocationResponseDto getLocationFromCoordinates(Double latitude, Double longitude) {
        // 1. Gọi Google Geocoding API để lấy thông tin địa lý
        LocationResponse locationResponse = googleGeocodingService.reverseGeocode(latitude, longitude);

        // 2. Build DTO trả về cho client
        if (locationResponse == null) {
            return null;
        }

        LocationResponseDto dto = new LocationResponseDto();
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setRoad(joinNotNull(locationResponse.getStreetNumber(), locationResponse.getStreet()));
        dto.setWard(locationResponse.getWard());
        dto.setDistrict(locationResponse.getDistrict());
        dto.setCity(locationResponse.getCity());
        dto.setCountry(locationResponse.getCountry());
        dto.setCountryCode(locationResponse.getPostalCode());
        dto.setFullAddress(locationResponse.getFullAddress());

        return dto;
    }

}
