package com.pomina.app.product_warranty_activation_scanning.converter;

import com.pomina.app.product_warranty_activation_scanning.dto.response.ProductDetailResponseDto;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScanProductWarrantyConverter {
    ProductDetailResponseDto toProductDetailResponseDto(Product product);
}
