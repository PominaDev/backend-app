package com.pomina.webapp.user_role_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.master_location_managerment.dto.MasterLocationDto;
import com.pomina.webapp.user_role_managerment.converter.SysRoleConverter;
import com.pomina.webapp.user_role_managerment.dto.SysRoleDto;
import com.pomina.webapp.user_role_managerment.service.SysUserRoleManagermentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiSysRole.BASE)
@RequiredArgsConstructor
public class SysRoleController {
    private final SysUserRoleManagermentService sysUserRoleManagermentService;

    @GetMapping(ApiConstants.ApiSysRole.GET_ALL_ROLE)
    public ResponseEntity<ApiResponse<List<SysRoleDto>>> getAllRole() {
        return ResponseHandler.success(sysUserRoleManagermentService.findAll());
    }
}
