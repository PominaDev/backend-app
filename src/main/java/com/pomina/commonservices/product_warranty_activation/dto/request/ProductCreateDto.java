package com.pomina.commonservices.product_warranty_activation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
