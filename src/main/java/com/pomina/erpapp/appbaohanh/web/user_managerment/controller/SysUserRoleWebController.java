package com.pomina.erpapp.appbaohanh.web.user_managerment.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.user_managerment.converter.SysUserRoleWebConverter;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.request.SysUserRoleWebCreateDto;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.request.SysUserRoleWebUpdateDto;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.erpapp.appbaohanh.web.user_managerment.service.SysUerRoleWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiUserRoleWeb.BASE)
@RequiredArgsConstructor
public class SysUserRoleWebController extends BaseController<SysUserRoleWebCreateDto, SysUserRoleWebUpdateDto, SysUserRoleWebResponseDto, Integer> {

    // Dependency Injection
    private final SysUerRoleWebService sysUerRoleWebService;
    private final SysUserRoleWebConverter sysUserRoleWebConverter;

    @Override
    public ResponseEntity<ApiResponse<Integer>> create(SysUserRoleWebCreateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<SysUserRoleWebResponseDto>> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<PageResponse<SysUserRoleWebResponseDto>>> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> update(Integer id, SysUserRoleWebUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> delete(Integer id) {
        return null;
    }

    @GetMapping(ApiConstants.ApiUserRoleWeb.GET_ALL)
    public ResponseEntity<ApiResponse<List<SysUserRoleWebResponseDto>>> getAll() {
        return ResponseHandler.success(sysUerRoleWebService.getAllUserRoleWeb());
    }
}
