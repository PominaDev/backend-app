package com.pomina.commonservices.category.service.impl;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import com.pomina.commonservices.category.dto.response.CategoryResponseDto;
import com.pomina.commonservices.category.mapper.VCategoryMapper;
import com.pomina.commonservices.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final VCategoryMapper vCategoryMapper;

    @Override
    public List<CategoryResponseDto> getCategoriesList() {

        List<CategoryItem> categoriesList = vCategoryMapper.findAllViewCategories();
        if (categoriesList == null || categoriesList.isEmpty()) {
            return Collections.emptyList();
        }

        // Group theo nameGroup
        Map<String, List<CategoryItem>> grouped =
                categoriesList.stream()
                        .collect(Collectors.groupingBy(CategoryItem::getNameGroup));

        // Convert sang list CategoryGroupDto
        return grouped.entrySet().stream()
                .map(entry -> new CategoryResponseDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}
