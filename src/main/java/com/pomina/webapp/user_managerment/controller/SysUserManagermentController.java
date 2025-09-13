package com.pomina.webapp.user_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.service.SysUserManagermentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiSysUserManagement.BASE)
@RequiredArgsConstructor
public class SysUserManagermentController {

    private final SysUserManagermentService sysUserService;

    @GetMapping(ApiConstants.ApiSysUserManagement.GET_ALL_USERS)
    public ResponseEntity<ApiResponse<List<SysUserResponeDto>>> getAllUsers() {
        return ResponseHandler.success(sysUserService.findAll());
    }

    @GetMapping(ApiConstants.ApiSysUserManagement.GET_USERS_PAGED)
    public ResponseEntity<ApiResponse<PageResponse<SysUserResponeDto>>> getUsersPaged(
            @RequestParam(defaultValue = "1") Integer page,  // Trang bắt đầu từ 1
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) List<String> filter,
            @RequestParam(required = false) List<String> roleNames) {
        return ResponseHandler.success(sysUserService.findAllPaged(filter, roleNames, page, size));
    }

    @PostMapping(ApiConstants.ApiSysUserManagement.CREATE)
    public ResponseEntity<ApiResponse<Integer>> createUser(@Valid @RequestBody SysUserRequestDto request) {
        Integer id = sysUserService.insert(request);
        return ResponseHandler.success(id);
    }

    @PutMapping(ApiConstants.ApiSysUserManagement.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> updateUser(@PathVariable Integer id,
                                                           @Valid @RequestBody SysUserRequestDto request) {
        request.setUserId(id); // đảm bảo đồng bộ ID
        return ResponseHandler.success(sysUserService.update(request));
    }

    @DeleteMapping(ApiConstants.ApiSysUserManagement.DELETE)
    public ResponseEntity<ApiResponse<Integer>> deleteUser(@PathVariable Integer id) {
        return ResponseHandler.success(sysUserService.softDeleteById(id));
    }
}
