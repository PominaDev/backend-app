package com.pomina.webapp.menu_permission.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.menu_permission.converter.MasterPermissionConverter;
import com.pomina.webapp.menu_permission.dto.request.MasterPermissionCreateDto;
import com.pomina.webapp.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.webapp.menu_permission.dto.request.MasterPermissionUpdateDto;
import com.pomina.webapp.menu_permission.dto.response.MasterPermissionResponseDto;
import com.pomina.webapp.menu_permission.service.MasterPermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiMasterPermission.BASE)
@RequiredArgsConstructor
public class MasterPermissionController extends BaseController<MasterPermissionCreateDto, MasterPermissionUpdateDto, MasterPermissionResponseDto, Integer> {

  // Dependency injection
  private final MasterPermissionService masterPermissionService;

  private final MasterPermissionConverter masterPermissionConverter;

  @Override
  @PostMapping(ApiConstants.ApiMasterPermission.CREATE)
  public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody MasterPermissionCreateDto dto) {
    MasterPermissionRequestDto requestDto = masterPermissionConverter.toMasterPermissionRequestDto(dto);
    return ResponseHandler.success(masterPermissionService.create(requestDto));
  }

  @Override
  @GetMapping(ApiConstants.ApiMasterPermission.GET_BY_ID)
  public ResponseEntity<ApiResponse<MasterPermissionResponseDto>> getById(@PathVariable("id") Integer id) {
    return ResponseHandler.success(masterPermissionService.getById(id));
  }

  @Override
  @GetMapping(ApiConstants.ApiMasterPermission.SEARCH)
  public ResponseEntity<ApiResponse<PageResponse<MasterPermissionResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
    return ResponseHandler.success(masterPermissionService.search(pageRequest));
  }

  @Override
  @PostMapping(ApiConstants.ApiMasterPermission.UPDATE)
  public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody MasterPermissionUpdateDto dto) {
    MasterPermissionRequestDto requestDto = masterPermissionConverter.toMasterPermissionRequestDto(dto);
    return ResponseHandler.success(masterPermissionService.update(id, requestDto));
  }

  @Override
  @DeleteMapping(ApiConstants.ApiMasterPermission.DELETE)
  public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
    return ResponseHandler.success(masterPermissionService.delete(id));
  }
}
