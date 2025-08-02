package com.pomina.webapp.product_warranty_activation_tracking.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class ProductWarrantyController {

    private final WarrantyService warrantyService;

    @GetMapping(ApiConstants.ApiWarranty.GET_WARRANTY_INFO_HISTORY_ALL)
    public ResponseEntity<ApiResponse<PageResponse<WarrantyInfoHistory>>> getAllWarrantyInfoHistory(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(warrantyService.getWarrantyInfoHistory(pageRequest, true));
    }
}
