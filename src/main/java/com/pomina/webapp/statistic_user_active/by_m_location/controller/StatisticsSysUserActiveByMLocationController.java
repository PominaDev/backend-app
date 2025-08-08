package com.pomina.webapp.statistic_user_active.by_m_location.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.statistic_user_active.by_m_location.dto.respone.StatisticsSysUserActiveByMLocationResponseDto;
import com.pomina.webapp.statistic_user_active.by_m_location.service.StatisticsSysUserActiveByMLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiStatisticsSysUerActiveByMLocation.BASE)
@RequiredArgsConstructor
public class StatisticsSysUserActiveByMLocationController {
    // Dependency injection
    private final StatisticsSysUserActiveByMLocationService statisticsSysUserActiveByMLocationService;

    @GetMapping(ApiConstants.ApiStatisticsSysUerActiveByMLocation.GET_ALL)
    public ResponseEntity<ApiResponse<List<StatisticsSysUserActiveByMLocationResponseDto>>> getAllStatisticsSysUserActiveByMLocation() {
        return ResponseHandler.success(statisticsSysUserActiveByMLocationService.getAllUserActiveMLocation());
    }
}
