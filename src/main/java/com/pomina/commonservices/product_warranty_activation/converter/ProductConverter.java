package com.pomina.commonservices.product_warranty_activation.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductCreateDto;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductUpdateDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.VProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter extends BaseConverter<ProductRequestDto, ProductResponseDto, Product> {

    // Mapping from CreateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductCreateDto createDto);

    // Mapping from UpdateDto to RequestDto
    ProductRequestDto toProductRequestDto(ProductUpdateDto updateDto);

    // Mapping from RequestDto to Entity (exclude productId to avoid overwriting)
    @Mapping(target = "productId", ignore = true)
    // ignore productId vì nếu body có trường productId người dùng update thì không công cập nhật productId người dung truyền vào mà cập nhật theo productId trong findById
    void updateEntityFromDto(ProductRequestDto dto, @MappingTarget Product entity);

    List<VProductResponseDto> toVProductResponseList(List<Product> productList);
}
