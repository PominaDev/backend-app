package com.pomina.commonservices.category.dto.response;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponseDto {

    private String nameGroup;

    private List<CategoryItem> data;
}
