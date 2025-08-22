package com.pomina.commonservices.category.service.impl;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import com.pomina.commonservices.category.dto.response.CategoryResponseDto;
import com.pomina.commonservices.category.enums.CategoryEnum;
import com.pomina.commonservices.category.mapper.VCategoryMapper;
import com.pomina.commonservices.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
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

        // Group các CategoryItem theo nameGroup
        // Ví dụ: "loại tôn" -> List<CategoryItem>, "độ mạ" -> List<CategoryItem>
        Map<String, List<CategoryItem>> grouped =
                categoriesList.stream()
                        .collect(Collectors.groupingBy(
                                CategoryItem::getNameGroup,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        if (grouped == null || grouped.isEmpty()) {
            return Collections.emptyList();
        }

        // Tạo đối tượng CategoryResponseDto bằng cách duyệt qua toàn bộ CategoryEnum
        // Mỗi enum sẽ biết cách map vào field tương ứng trong DTO
        CategoryResponseDto dto = Arrays.stream(CategoryEnum.values())
                .collect(
                        // Supplier: khởi tạo DTO rỗng
                        CategoryResponseDto::new,

                        // Accumulator: với mỗi CategoryEnum e,
                        // lấy list items tương ứng từ grouped map (nếu không có thì emptyList)
                        // rồi gọi e.apply(dto, items) để set vào field trong dto
                        (res, e) -> e.apply(
                                res,
                                grouped.getOrDefault(e.getNameGroup(), Collections.emptyList())
                        ),
                        // Combiner: không cần merge vì DTO chỉ tạo từ stream tuần tự
                        (a, b) -> {}
                );

        if (dto == null || List.of(dto).isEmpty()) {
            return Collections.emptyList();
        }

        return List.of(dto);
    }
}
