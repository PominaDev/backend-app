package com.pomina.webapp.user_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleMobileResponseDto;
import com.pomina.webapp.user_managerment.service.SysUserRoleMobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiSysUserManagement.BASE)
@RequiredArgsConstructor
public class SysUserRoleMobileController {

    private final SysUserRoleMobileService sysUerRoleMobileService;

    @GetMapping(ApiConstants.ApiSysUserManagement.GET_USER_ACTIVE_ROLE_MOBILE)
    public ResponseEntity<ApiResponse<List<SysUserRoleMobileResponseDto>>> getAllUserActiveRoleMobile() {
        return ResponseHandler.success(sysUerRoleMobileService.getAllUserActiveRoleMobile());
    }
}
