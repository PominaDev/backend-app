package com.pomina.commonservices.product_warranty_activation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @JsonProperty("mALength") đổi tên field JSON khi không trùng với ten biến trong java
 */
public class ProductCreateDto {
    private String maCuonTon;
    private String tenSanPham;
    private String loaiCuon;
    private Integer bhPhaiMau;
    private Integer bhAnMon;
    private Double totalWeight;
    private Double totalLength;
    @JsonProperty("mALength")
    private Double maLength;
    @JsonProperty("mBLength")
    private Double mbLength;
    @JsonProperty("mCLength")
    private Double mcLength;
}
