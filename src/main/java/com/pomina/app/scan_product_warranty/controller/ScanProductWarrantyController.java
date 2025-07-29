package com.pomina.app.scan_product_warranty.controller;

import com.pomina.app.scan_product_warranty.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.scan_product_warranty.dto.response.ScanProductWarrantyResponseDto;
import com.pomina.app.scan_product_warranty.service.ScanProductWarrantyService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class ScanProductWarrantyController {

    private final ScanProductWarrantyService scanProductWarrantyService;

    @PostMapping("/scan-qr")
    public ResponseEntity<ApiResponse<ScanProductWarrantyResponseDto>> scanQr(@RequestBody ScanProductWarrantyRequestDto requestDto) {
        return ResponseHandler.success(scanProductWarrantyService.scanQr(requestDto));
    }
}
