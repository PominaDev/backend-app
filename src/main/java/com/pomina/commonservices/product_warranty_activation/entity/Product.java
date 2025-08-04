package com.pomina.commonservices.product_warranty_activation.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    private Integer productId;
    private String maCuonTon;
    private String tenSanPham;
    private String loaiCuon;
    private Integer bhPhaiMau;
    private Integer bhAnMon;
    private Double totalWeight;
    private Double totalLength;
    private Double mALength;
    private Double mBLength;
    private Double mCLength;

    // JOIN with u_warranty table
    private Warranty warranty;
}
