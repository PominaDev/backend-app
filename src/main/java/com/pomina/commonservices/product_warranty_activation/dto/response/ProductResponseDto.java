package com.pomina.commonservices.product_warranty_activation.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponseDto {
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


//    private Integer tonKho;
//    private String dongTon;
//    private Double gia;
//    private Double doDay;
//    private Double doRong;
//    private Double doPhu;
//    private String thuongHieu;
//    private String mauSac;
//    private String xuatXu;
//    private String moTa;
}
