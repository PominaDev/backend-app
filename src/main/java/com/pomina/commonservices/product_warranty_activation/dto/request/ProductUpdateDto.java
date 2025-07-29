package com.pomina.commonservices.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    private Integer productId;
    private String maCuonTon;
    private String tenSanPham;
    private Double totalWeight;
    private Double totalLength;
    private String loaiCuon;
    private Integer bhPhaiMau;
    private Integer bhAnMon;
    private Double mALength;
    private Double mBLength;
    private Double mCLength;
    private String status;
}
