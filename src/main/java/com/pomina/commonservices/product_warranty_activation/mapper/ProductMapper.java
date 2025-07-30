package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * Tìm thông tin sản phầm và bảo hành theo mã cuộn tôn -> SCAN QR
     *
     * @param maCuonTon -> u.product.ma_cuon_ton
     * @return Product
     */
    Product findProductAndWarrantyByMaCuonTon(String maCuonTon);
}
