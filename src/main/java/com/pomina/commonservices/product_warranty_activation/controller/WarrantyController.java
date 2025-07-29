package com.pomina.commonservices.product_warranty_activation.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrantyService warrantyService;

    @GetMapping(ApiConstants.ApiWarranty.GET_BY_ID)
    public ResponseEntity<ApiResponse<WarrantyResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(warrantyService.getById(id));
    }
}
