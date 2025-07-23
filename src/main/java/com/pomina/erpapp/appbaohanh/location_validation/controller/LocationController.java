package com.pomina.erpapp.appbaohanh.location_validation.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationCreateDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationRequestDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationUpdateDto;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.CheckLocationResponse;
import com.pomina.erpapp.appbaohanh.location_validation.dto.response.LocationResponseDto;
import com.pomina.erpapp.appbaohanh.location_validation.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiLocation.BASE)
@RequiredArgsConstructor
public class LocationController extends BaseController<LocationCreateDto, LocationUpdateDto, LocationResponseDto, Long> {

    private final LocationService locationService;

    @Override
    public ResponseEntity<ApiResponse<Integer>> create(LocationCreateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<LocationResponseDto>> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<PageResponse<LocationResponseDto>>> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> update(Long id, LocationUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> delete(Long id) {
        return null;
    }

    @PostMapping(ApiConstants.ApiLocation.REGISTER)
    public ResponseEntity<ApiResponse<LocationResponseDto>> register(@RequestBody @Validated LocationRequestDto locationRequestDto) {
        return ResponseHandler.success(locationService.registerLocation(locationRequestDto));
    }

    @PostMapping(ApiConstants.ApiLocation.VALIDATE)
    public ResponseEntity<ApiResponse<CheckLocationResponse>> validate(@RequestBody @Validated LocationRequestDto locationRequestDto) {
        return ResponseHandler.success(locationService.checkLocation(locationRequestDto));
    }
}
