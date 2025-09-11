package com.pomina.commonservices.product_warranty_activation.dto.custom_mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarrantyInfoHistory {
    // User Info
    private Integer userId;
    private String hoVaTen;
    private String phoneNumber;

    // Product Info
    private String maCuonTon;
    private String tenSanPham;
    private String loaiCuon;
    private Double totalWeight;
    private Double totalLength;
    private Double mALength;
    private Double mBLength;
    private Double mCLength;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bhPhaiMau;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bhAnMon;

    // Location - Account (from u_location)
    private String locationAccount01;
    private String locationAccount02;
    private String locationAccount03;
    private String locationAccount04;
    private Double locationAccountLat;
    private Double locationAccountLong;

    // Location - Warranty (from u_warranty)
    private Integer warrantyId;
    private String locationWarranty01;
    private String locationWarranty02;
    private String locationWarranty03;
    private String locationWarranty04;
    private Double locationWarrantyLat;
    private Double locationWarrantyLong;

    // Warranty Time Periods
    private String fromWarrantyAnMon;
    private String toWarrantyAnMon;
    private String fromWarrantyPhaiMau;
    private String toWarrantyPhaiMau;

    private String status;
    private Boolean isValid;
    private String noted;
}
