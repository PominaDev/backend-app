package com.pomina.app.category.mapper;

import com.pomina.app.category.dto.custom_mapper.CategoryItem;
import com.pomina.common.model.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VCategoryMapper {

    List<CategoryItem> findAllViewCategories(@Param("offset") int offset,
                                             @Param("limit") int limit,
                                             @Param("paging") PageRequest pageRequest);

    int countFindAllViewCategories();
}
