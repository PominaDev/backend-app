package com.pomina.commonservices.category.mapper;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VCategoryMapper {

    List<CategoryItem> findAllViewCategories();
}
