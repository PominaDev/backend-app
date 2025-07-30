package com.pomina.app.scan_product_warranty.controller;

import com.pomina.app.scan_product_warranty.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.scan_product_warranty.dto.response.ScanProductWarrantyResponseDto;
import com.pomina.app.scan_product_warranty.service.ScanProductWarrantyService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class ScanProductWarrantyController {

    private final ScanProductWarrantyService scanProductWarrantyService;

    private final WarrantyService warrantyService;

    @PostMapping(ApiConstants.ApiWarranty.ACTIVATE_BY_QR)
    public ResponseEntity<ApiResponse<ScanProductWarrantyResponseDto>> activateByQrCode(@RequestBody ScanProductWarrantyRequestDto requestDto) {
        return ResponseHandler.success(scanProductWarrantyService.activateByQrCode(requestDto));
    }

    @GetMapping(ApiConstants.ApiWarranty.GET_WARRANTY_INFO_HISTORY)
    public ResponseEntity<ApiResponse<List<WarrantyInfoHistory>>> getWarrantyInfoHistory() {
        return ResponseHandler.success(warrantyService.getWarrantyInfoHistory(false));
    }
}
