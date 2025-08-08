package com.pomina.webapp.statistic_user_active.by_role.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.statistic_user_active.by_role.dto.respone.StatisticsSysUserActiveByRoleResponseDto;
import com.pomina.webapp.statistic_user_active.by_role.service.StatisticsSysUserActiveByRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiStatisticsSysUerActiveByRole.BASE)
@RequiredArgsConstructor
public class StatisticsSysUserActiveByRoleController {
    // Dependency injection
    private final StatisticsSysUserActiveByRoleService statisticsSysUserActiveByRoleService;

    @GetMapping(ApiConstants.ApiStatisticsSysUerActiveByRole.GET_ALL)
    public ResponseEntity<ApiResponse<List<StatisticsSysUserActiveByRoleResponseDto>>> getAllStatisticsSysUserActiveByRole() {
        return ResponseHandler.success(statisticsSysUserActiveByRoleService.getAllUserActiveRole());
    }
}
