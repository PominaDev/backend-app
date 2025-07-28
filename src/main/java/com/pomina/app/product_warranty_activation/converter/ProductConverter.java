package com.pomina.app.product.warranty.activation.converter;

import com.pomina.app.product.warranty.activation.dto.request.ProductCreateDto;
import com.pomina.app.product.warranty.activation.dto.request.ProductRequestDto;
import com.pomina.app.product.warranty.activation.dto.request.ProductUpdateDto;
import com.pomina.app.product.warranty.activation.dto.response.ProductResponseDto;
import com.pomina.app.product.warranty.activation.entity.Product;
import com.pomina.common.converter.BaseConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter extends BaseConverter<ProductRequestDto, ProductResponseDto, Product> {
    // Mapping from CreateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductCreateDto createDto);

    // Mapping from UpdateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductUpdateDto updateDto);
}
