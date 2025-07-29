package com.pomina.app.scan_product_warranty.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponseDto {
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
    private LocalDateTime updatedAt;
}
