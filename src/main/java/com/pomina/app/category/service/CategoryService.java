package com.pomina.app.category.service;

import com.pomina.app.category.dto.response.CategoryResponseDto;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;

public interface CategoryService {
    PageResponse<CategoryResponseDto> getCategoriesList(PageRequest pageRequest);
}
