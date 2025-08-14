package com.pomina.webapp.pricing_policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuCreateDto;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiPricingPolicyManagement.BASE)
@RequiredArgsConstructor
public class ChinhSachExcelController {

    private final ChinhSachExcelService chinhSachExcelService;

    @PostMapping(ApiConstants.ApiPricingPolicyManagement.IMPORT_LIST)
    public ResponseEntity<ApiResponse<Integer>> create(@RequestBody ChinhSachExcelCreateDto chinhSachExcelCreateDto) {
        return ResponseHandler.success(chinhSachExcelService.createList(chinhSachExcelCreateDto));
    }
}
