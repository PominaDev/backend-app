package com.pomina.erpapp.appbaohanh.product_warranty_activation.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.ProductConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Product;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.mapper.ProductMapper;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.ProductService;
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
    @Transactional
    public int create(ProductRequestDto dto) {
        Product product = productConverter.toEntity(dto);
        return productMapper.insert(product);
    }

    @Override
    public int update(Integer id, ProductRequestDto dto) {
        Product product = productConverter.toEntity(dto);
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

        List<ProductResponseDto> productResponse = productConverter.toResponseList(productInfoList);

        int totalElements = productMapper.countAll();

        return PageResponse.createPaged(productResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @Override
    public int delete(Integer id) {return productMapper.deleteById(id); }
}
