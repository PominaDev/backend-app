package com.pomina.app.category.dto.response;

import com.pomina.app.category.dto.custom_mapper.CategoryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {

    private List<CategoryItem> loaiTon;

    private List<CategoryItem> doMa;

    private List<CategoryItem> macThep;

    private List<CategoryItem> mauSac;

    private List<CategoryItem> loaiHang;
}
