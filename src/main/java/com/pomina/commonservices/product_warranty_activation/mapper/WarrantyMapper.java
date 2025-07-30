package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarrantyMapper extends BaseMapper<Warranty> {

    /**
     * Truy xuất thông tin bảo hành của sản phẩm,
     * kết hợp thông tin người dùng,
     * địa chỉ đăng ký,
     * và thông tin sản phẩm
     *
     * @param userId
     * @return List<WarrantyInfoHistory>
     */
    List<WarrantyInfoHistory> findWarrantyDetail(@Param("userId") Integer userId);
}
