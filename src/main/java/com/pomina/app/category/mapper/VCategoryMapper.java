package com.pomina.app.category.mapper;

import com.pomina.app.category.dto.custom_mapper.CategoryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VCategoryMapper {

    List<CategoryItem> findAllViewCategories(@Param("offset") int offset,
                                             @Param("limit") int limit);

    int countFindAllViewCategories();
}
