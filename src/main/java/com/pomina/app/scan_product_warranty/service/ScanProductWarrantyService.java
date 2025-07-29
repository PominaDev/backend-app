package com.pomina.app.scan_product_warranty.service;

import com.pomina.app.scan_product_warranty.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.scan_product_warranty.dto.response.ScanProductWarrantyResponseDto;

public interface ScanProductWarrantyService {

    ScanProductWarrantyResponseDto scanQr(ScanProductWarrantyRequestDto scanProductWarrantyRequestDto);
}
