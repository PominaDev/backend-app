package com.pomina.app.scan_product_warranty.converter;

import com.pomina.app.scan_product_warranty.dto.response.ProductDetailResponseDto;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScanProductWarrantyConverter {
    ProductDetailResponseDto toProductDetailResponseDto(Product product);
}
