package com.pomina.commonservices.product_warranty_activation.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * Tìm thông tin sản phầm và bảo hành theo mã cuộn tôn -> SCAN QR
     *
     * @param maCuonTon -> u.product.ma_cuon_ton
     * @return Product
     */
    Product findProductAndWarrantyByMaCuonTon(@Param("maCuonTon") String maCuonTon);

    /**
     * Kiểm tra xem mã cuộn tôn lúc insert đã có hay chưa
     * @param maCuonTon -> u.product.ma_cuon_ton
     * @return boolean
     */
    boolean existsByMaCuonTon(@Param("maCuonTon") String maCuonTon);

    int insertBatch(@Param("list") List<Product> productList);
}
