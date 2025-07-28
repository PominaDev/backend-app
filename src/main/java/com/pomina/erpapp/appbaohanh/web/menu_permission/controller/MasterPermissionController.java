package com.pomina.erpapp.appbaohanh.web.menu_permission.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.menu_permission.converter.MasterPermissionConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionCreateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionUpdateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterPermissionService;
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

import java.util.List;


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

  @PostMapping(ApiConstants.ApiMasterPermission.CREATE_LIST)
  public ResponseEntity<ApiResponse<Integer>> createList(@RequestBody List<MasterPermissionCreateDto> createDtoList) {
    List<MasterPermissionRequestDto> requestDtoList = masterPermissionConverter.fromCreateListToRequestList(createDtoList);
    return ResponseHandler.success(masterPermissionService.createListPermisstion(requestDtoList));
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
  @PostMapping(ApiConstants.ApiMasterPermission.UPDATE_LIST)
  public ResponseEntity<ApiResponse<Integer>> updateList(@RequestBody List<MasterPermissionUpdateDto> updateDtoList) {
    List<MasterPermissionRequestDto> requestDtoList = masterPermissionConverter.fromUpdateListToRequestList(updateDtoList);
    return ResponseHandler.success(masterPermissionService.updateListPermisstion(requestDtoList));
  }


  @Override
  @DeleteMapping(ApiConstants.ApiMasterPermission.DELETE)
  public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
    return ResponseHandler.success(masterPermissionService.delete(id));
  }
}
