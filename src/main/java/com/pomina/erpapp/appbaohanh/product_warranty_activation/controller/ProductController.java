package com.pomina.erpapp.appbaohanh.product_warranty_activation.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.ProductConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.ProductCreateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.ProductRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.ProductUpdateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.ProductResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiProduct.BASE)
@RequiredArgsConstructor
public class ProductController extends BaseController<ProductCreateDto, ProductUpdateDto, ProductResponseDto, Integer> {

    private final ProductService productService;

    private final ProductConverter productConverter;

    @Override
    @PostMapping(ApiConstants.ApiProduct.CREATE)
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody ProductCreateDto dto) {
        ProductRequestDto requestDto = productConverter.toProductRequestDto(dto);
        return ResponseHandler.success(productService.create(requestDto));
    }

    @Override
    @GetMapping(ApiConstants.ApiProduct.GET_BY_ID)
    public ResponseEntity<ApiResponse<ProductResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(productService.getById(id));
    }

    @Override
    @GetMapping(ApiConstants.ApiProduct.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(productService.search(pageRequest));
    }

    @Override
    @PostMapping(ApiConstants.ApiProduct.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody ProductUpdateDto dto) {
        ProductRequestDto requestDto = productConverter.toProductRequestDto(dto);
        return ResponseHandler.success(productService.update(id, requestDto));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiProduct.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(productService.delete(id));
    }
}
