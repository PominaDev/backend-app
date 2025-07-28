package com.pomina.webapp.user_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.webapp.user_managerment.service.SysUerRoleWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiSysUserManagement.BASE)
@RequiredArgsConstructor
public class SysUserRoleWebController {
    // Dependency Injection
    private final SysUerRoleWebService sysUerRoleWebService;

    @GetMapping(ApiConstants.ApiSysUserManagement.GET_USER_ACTIVE_ROLE_WEB)
    public ResponseEntity<ApiResponse<List<SysUserRoleWebResponseDto>>> getAllUserActiveRoleWeb() {
        return ResponseHandler.success(sysUerRoleWebService.getAllUserActiveRoleWeb());
    }
}
