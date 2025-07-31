package com.pomina.webapp.location.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.user_management.dto.response.RoleResponseDto;
import com.pomina.webapp.location.dto.request.LocationRequestCreateDto;
import com.pomina.webapp.location.dto.request.LocationRequestUpdateDto;
import com.pomina.webapp.location.service.Impl.LocationWebServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.ApiLocationWeb.BASE)
@RequiredArgsConstructor
public class LocationWebController extends BaseController<LocationRequestCreateDto, LocationRequestUpdateDto, RoleResponseDto, Integer> {
    private final LocationWebServiceImpl locationService;

    @PostMapping(ApiConstants.ApiLocationWeb.CREATE_WEB)
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody LocationRequestCreateDto dto) {
        return ResponseHandler.success(locationService.createLocation(dto));
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<RoleResponseDto>> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<PageResponse<RoleResponseDto>>> search(PageRequest pageRequest) {
        return null;
    }

    @PutMapping(ApiConstants.ApiLocationWeb.UPDATE_WEB)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("userId") Integer userId,@Valid @RequestBody LocationRequestUpdateDto dto) {
        return ResponseHandler.success(locationService.updateLocation(userId, dto));
    }
}
