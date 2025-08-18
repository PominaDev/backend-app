package com.pomina.commonservices.category.dto.response;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto {

    private List<CategoryItem> loaiTon;

    private List<CategoryItem> loaiHang;

    private List<CategoryItem> beMat;

    private List<CategoryItem> doMa;

    private List<CategoryItem> doDay;

    private List<CategoryItem> mauSac;

    private List<CategoryItem> macThep;
}
