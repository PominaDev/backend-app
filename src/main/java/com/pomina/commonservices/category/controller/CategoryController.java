package com.pomina.commonservices.category.controller;

import com.pomina.commonservices.category.dto.response.CategoryResponseDto;
import com.pomina.commonservices.category.service.CategoryService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiCategory.BASE)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Description: Lấy danh sách thông tin category theo các name group: loại tôn, độ mạ, mác thép, màu sắc, loại hàng.
     * <p>
     * Endpoint: /api/v1/categories
     *
     * @return {@link List<CategoryResponseDto>} -
     * Danh sách thông tin category
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategoriesList() {
        return ResponseHandler.success(categoryService.getCategoriesList());
    }
}
