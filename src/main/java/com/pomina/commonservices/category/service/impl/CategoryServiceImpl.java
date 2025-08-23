package com.pomina.commonservices.category.service.impl;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import com.pomina.commonservices.category.dto.response.CategoryResponseDto;
import com.pomina.commonservices.category.mapper.VCategoryMapper;
import com.pomina.commonservices.category.service.CategoryService;
import com.pomina.commonservices.category.utils.NameGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final VCategoryMapper vCategoryMapper;

    private static final Set<String> ALLOWED_MULTIPLE_FIELDS = Set.of(
            "mauSac", "doMa", "macThep"
    );

    @Override
    public List<CategoryResponseDto> getCategoriesList() {

        List<CategoryItem> categoriesList = vCategoryMapper.findAllViewCategories();
        if (categoriesList == null || categoriesList.isEmpty()) {
            return Collections.emptyList();
        }

        // Group theo nameGroup
        Map<String, List<CategoryItem>> grouped =
                categoriesList.stream()
                        .collect(Collectors.groupingBy(
                                CategoryItem::getNameGroup,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        // Convert sang list CategoryGroupDto
        return grouped.entrySet().stream()
                .map(entry -> {
                    // Map NameGroup to key response
                    String key = NameGroupMapper.mapNameGroupToKey(entry.getKey());

                    // Set isMultiple = true if multiple options to filter api POST /products/search
                    boolean isMultiple = ALLOWED_MULTIPLE_FIELDS.contains(key);

                    return new CategoryResponseDto(
                            key,
                            entry.getKey(),
                            entry.getValue(),
                            isMultiple
                    );
                })
                .toList();
    }
}
