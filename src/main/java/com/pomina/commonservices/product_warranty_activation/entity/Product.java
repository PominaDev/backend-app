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
    private String maHang;
    private String maCuonTon;
    private String tenSanPham;
    private String loaiCuon;
    private Double totalWeight;
    private Double totalLength;
    private Double maLength;
    private Double mbLength;
    private Double mcLength;
    private Double tyTrongThucTe;
    private Double doDay;
    private Double khoRong;
    private String loaiHang;
    private String macThep;
    private String doMa;
    private String ghiChu;
    private String thanhPham;
    private String kho;
    private String xuatHoaDon;
    private String khachChot;
    private String soDonHang;
    private Boolean isVisible;
    private Integer bhPhaiMau;
    private Integer bhAnMon;
    private String mau;

    // JOIN with u_warranty table
    private Warranty warranty;
}
