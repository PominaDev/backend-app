package com.pomina.app.category.controller;

import com.pomina.app.category.dto.response.CategoryResponseDto;
import com.pomina.app.category.service.CategoryService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiCategory.BASE)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Description: Lấy thông tin sản phẩm theo các name group: loại tôn, độ mạ, mác thép, màu sắc, loại hàng.
     * <p>
     * Endpoint: /api/v1/categories
     *
     * @param pageRequest Đối tượng chứa thông tin phân trang và sắp xếp (tùy chọn)
     * @return {@link CategoryResponseDto} -
     * Thông tin sản phẩm
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDto>>> getCategoriesList(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(categoryService.getCategoriesList(pageRequest));
    }
}
