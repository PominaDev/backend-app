package com.pomina.app.category.service.impl;

import com.pomina.app.category.dto.custom_mapper.CategoryItem;
import com.pomina.app.category.dto.response.CategoryResponseDto;
import com.pomina.app.category.enums.CategoryEnum;
import com.pomina.app.category.mapper.VCategoryMapper;
import com.pomina.app.category.service.CategoryService;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final VCategoryMapper vCategoryMapper;

    @Override
    public PageResponse<CategoryResponseDto> getCategoriesList(PageRequest pageRequest) {
        List<CategoryItem> categoriesList = vCategoryMapper.findAllViewCategories(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest
        );

        if (categoriesList == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        // Group các CategoryItem theo nameGroup
        // Ví dụ: "loại tôn" -> List<CategoryItem>, "độ mạ" -> List<CategoryItem>
        Map<String, List<CategoryItem>> grouped =
                categoriesList.stream()
                        .collect(Collectors.groupingBy(CategoryItem::getNameGroup));

        if (grouped == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
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

        if (dto == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        int totalElements = vCategoryMapper.countFindAllViewCategories();

        return PageResponse.createPaged(List.of(dto), pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }
}
