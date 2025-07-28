package com.pomina.webapp.user_managerment.controller;

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
@RequestMapping({"/api/v1/user-role-webs"})
@RequiredArgsConstructor
public class SysUserRoleWebController {
    // Dependency Injection
    private final SysUerRoleWebService sysUerRoleWebService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<SysUserRoleWebResponseDto>>> getAll() {
        return ResponseHandler.success(sysUerRoleWebService.getAll());
    }
}
