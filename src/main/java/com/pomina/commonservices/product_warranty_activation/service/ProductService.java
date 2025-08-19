package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.ProductFilter;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.VProductResponseDto;

import java.util.List;

public interface ProductService extends BaseService<ProductRequestDto, ProductResponseDto, Integer> {

    int insertBatch(List<ProductRequestDto> list);

    PageResponse<VProductResponseDto> getProductsByFilter(PageRequest pageRequest, ProductFilter productFilter);
}
