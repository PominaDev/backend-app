package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.common.model.PageRequest;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarrantyMapper extends BaseMapper<Warranty> {

    List<WarrantyInfoHistory> findWarrantyDetail(@Param("offset") int offset, @Param("limit") int limit, @Param("paging") PageRequest pageRequest, @Param("userId") Integer userId);

    int countWarrantyDetail(@Param("userId") Integer userId);

    WarrantyInfoHistory findWarrantyDetailByMaCuonTon(@Param("maCuonTon") String maCuonTon);

    int countWarrantyInfoHistory(@Param("userId") Integer userId, @Param("filter") List<String> filter, @Param("isValid") Boolean isValid);

    List<WarrantyInfoHistory> filterWarrantyDetail(@Param("filter") List<String> filter, @Param("isValid") Boolean isValid, @Param("orderByClause") String orderByClause, @Param("offset") int offset, @Param("limit") int limit, @Param("paging") PageRequest pageRequest, @Param("userId") Integer userId);

//    List<WarrantyInfoHistory> filterByStatus(@Param("isValid") boolean isValid, @Param("orderByClause") String orderByClause, @Param("offset") int offset, @Param("limit") int limit, @Param("paging") PageRequest pageRequest, @Param("userId") Integer userId);
}
