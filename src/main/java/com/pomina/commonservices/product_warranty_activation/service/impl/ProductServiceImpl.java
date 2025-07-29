package com.pomina.commonservices.product_warranty_activation.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.product_warranty_activation.converter.ProductConverter;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import com.pomina.commonservices.product_warranty_activation.mapper.ProductMapper;
import com.pomina.commonservices.product_warranty_activation.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // Dependency injection
    private final ProductMapper productMapper;
    private final ProductConverter productConverter;

    @Override
//    @Transactional
    public int create(ProductRequestDto dto) {
        if (dto == null) {
            throw new RuntimeException("Không tìm thấy product");
        }
        Product product = productConverter.toEntity(dto);
//        AuditUtil.insert(product); chưa cập nhật AuditUtil
        return productMapper.insert(product);
    }

    @Override
    public int update(Integer id, ProductRequestDto dto) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new RuntimeException("Không tìm thấy product với ID = " + id);
        }
        productConverter.updateEntityFromDto(dto, product);
//        AuditUtil.update(product); Chưa cập nhật AuditUtil
        return productMapper.update(product);
    }

    @Override
    public ProductResponseDto getById(Integer id) {
        Product productInfo = productMapper.findById(id);
        if (productInfo != null) {
            return productConverter.toResponse(productInfo);
        }
        return null;
    }

    @Override
    public PageResponse<ProductResponseDto> search(PageRequest pageRequest) {

        List<Product> productInfoList = productMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (productInfoList == null || productInfoList.isEmpty()) {
            return null;
        }

//        for (Product product : productInfoList) {
//            AuditUtil.search(product); Chưa cập nhật AuditUtil
//        }

        List<ProductResponseDto> productResponse = productConverter.toResponseList(productInfoList);


        int totalElements = productMapper.countAll();

        return PageResponse.createPaged(productResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @Override
    public int delete(Integer productId) {
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new RuntimeException("Không tìm thấy product với ID = " + productId);
        }
//        AuditUtil.delete(product, null);
        return productMapper.softDeleteById(productId);
    }

    @Override
//    @Transactional
    public int insertBatch(List<ProductRequestDto> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        List<Product> productList = productConverter.toEntityList(list);
        return productMapper.insertBatch(productList);
    }
}
