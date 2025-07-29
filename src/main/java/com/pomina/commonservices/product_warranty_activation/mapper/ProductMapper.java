package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    int insertBatch(@Param("list") List<Product> productList);
}
