package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.dto.request.WarrantyRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;

import java.util.List;

public interface WarrantyService extends BaseService<WarrantyRequestDto, WarrantyResponseDto, Integer> {

    List<WarrantyInfoHistory> getWarrantyInfoHistory(boolean forAdmin);
}
