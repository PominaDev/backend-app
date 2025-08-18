package com.pomina.commonservices.category.service;

import com.pomina.commonservices.category.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getCategoriesList();
}
