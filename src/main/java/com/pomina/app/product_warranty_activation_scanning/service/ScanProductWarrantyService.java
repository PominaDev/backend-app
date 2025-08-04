package com.pomina.app.product_warranty_activation_scanning.service;

import com.pomina.app.product_warranty_activation_scanning.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.product_warranty_activation_scanning.dto.response.ScanProductWarrantyResponseDto;

public interface ScanProductWarrantyService {

    ScanProductWarrantyResponseDto activateByQrCode(ScanProductWarrantyRequestDto scanProductWarrantyRequestDto);
}
