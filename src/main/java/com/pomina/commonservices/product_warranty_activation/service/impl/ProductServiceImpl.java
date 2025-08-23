package com.pomina.commonservices.product_warranty_activation.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.logging.LogService;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.product_warranty_activation.converter.ProductConverter;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.ProductFilter;
import com.pomina.commonservices.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.VProductResponseDto;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import com.pomina.commonservices.product_warranty_activation.mapper.ProductMapper;
import com.pomina.commonservices.product_warranty_activation.service.ProductService;
import com.pomina.commonservices.product_warranty_activation.utils.ProductFilterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // Dependency injection
    private final ProductMapper productMapper;

    private final ProductConverter productConverter;

    private final LogService log;

    @Override
    @Transactional
    public int create(ProductRequestDto dto) {
        if (productMapper.existsByMaCuonTon(dto.getMaCuonTon())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = productConverter.toEntity(dto);
        AuditUtil.insert(product);
        return productMapper.insert(product);
    }

    @Override
    public int update(Integer id, ProductRequestDto dto) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productConverter.updateEntityFromDto(dto, product);
        AuditUtil.update(product);
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
    public int delete(Integer productId) {
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new RuntimeException("Không tìm thấy product với ID = " + productId);
        }
        AuditUtil.softDelete(product, null);
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

    @Override
    public PageResponse<VProductResponseDto> getProductsByFilter(PageRequest pageRequest, ProductFilter productFilter) {

        // Validate productFilter
        ProductFilterValidator.validate(productFilter);

        List<Product> productInfoList = productMapper.findByLoaiCuonAndTenSanPham(productFilter,
                pageRequest.getOffset(),
                pageRequest.getSize());

        if (productInfoList == null || productInfoList.isEmpty()) {
            return null;
        }

        List<VProductResponseDto> productResponse = productConverter.toVProductResponseList(productInfoList);

        if (productResponse == null || productResponse.isEmpty()) {
            return null;
        }

        int totalElements = productMapper.countFindByLoaiCuonAndTenSanPham(productFilter);

        return PageResponse.createPaged(productResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }
}
