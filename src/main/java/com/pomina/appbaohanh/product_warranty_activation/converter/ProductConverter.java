package com.pomina.appbaohanh.product_warranty_activation.converter;

import com.pomina.appbaohanh.common.converter.BaseConverter;
import com.pomina.appbaohanh.product_warranty_activation.dto.request.ProductCreateDto;
import com.pomina.appbaohanh.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.appbaohanh.product_warranty_activation.dto.request.ProductUpdateDto;
import com.pomina.appbaohanh.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.appbaohanh.product_warranty_activation.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter extends BaseConverter<ProductRequestDto, ProductResponseDto, Product> {
    // Mapping from CreateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductCreateDto createDto);

    // Mapping from UpdateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductUpdateDto updateDto);
}
