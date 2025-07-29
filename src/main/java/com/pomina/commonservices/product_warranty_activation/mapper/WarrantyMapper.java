package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WarrantyMapper extends BaseMapper<Warranty> {

    /**
     * Tìm thông tin bảo hành theo mã cuộn tôn -> SCAN QR
     *
     * @param maCuonTon -> u.product.ma_cuon_ton
     * @return Warranty
     */
    Warranty findByMaCuonTon(@Param("maCuonTon") String maCuonTon);
}
