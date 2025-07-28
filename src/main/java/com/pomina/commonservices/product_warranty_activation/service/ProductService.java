package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;

public interface ProductService extends BaseService<ProductRequestDto, ProductResponseDto, Integer> {
}
