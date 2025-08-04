package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService extends BaseService<ProductRequestDto, ProductResponseDto, Integer> {
    int insertBatch(List<ProductRequestDto> list);
}
