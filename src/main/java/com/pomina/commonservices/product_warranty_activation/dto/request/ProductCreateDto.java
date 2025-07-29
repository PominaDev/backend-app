package com.pomina.commonservices.product_warranty_activation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
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
}
