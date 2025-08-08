package com.pomina.webapp.statistic_user_active.by_u_location.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.statistic_user_active.by_u_location.dto.respone.StatisticsSysUserActiveByULocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_u_location.service.StatisticsSysUserActiveByULocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiStatisticsSysUerActiveByULocation.BASE)
@RequiredArgsConstructor
public class StatisticsSysUserActiveByULocationController {
    // Dependency injection
    private final StatisticsSysUserActiveByULocationService statisticsSysUserActiveByULocationService;

    @GetMapping(ApiConstants.ApiStatisticsSysUerActiveByULocation.GET_ALL)
    public ResponseEntity<ApiResponse<List<StatisticsSysUserActiveByULocationResponseDto>>> getAllStatisticsSysUserActiveByULocation() {
        return ResponseHandler.success(statisticsSysUserActiveByULocationService.getAllUserActiveULocation());
    }
}
