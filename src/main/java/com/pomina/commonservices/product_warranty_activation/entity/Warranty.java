package com.pomina.commonservices.product_warranty_activation.entity;

import com.pomina.common.model.BaseEntity;
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
public class Warranty extends BaseEntity {

    private Integer warrantyId;

    private Integer userId;

    private Double latitude;

    private Double longitude;

    private String address01;          // Đường

    private String address02;          // Xã/Phường

    private String address03;          // Huyện/Quận

    private String address04;          // Tỉnh/Thành phố

    private String address05;          // Quốc gia

    private String countryCode;

    private String maCuonTon;

    private LocalDateTime fromWarrantyPhaiMau; // Ngày kích hoạt bảo hành phai màu

    private LocalDateTime toWarrantyPhaiMau; // Ngày kích hoạt bảo hành phai màu + năm kích hoạt từ u_product.bh_phai_mau

    private LocalDateTime fromWarrantyAnMon; // Ngày kích hoạt bảo hành ăn mòn

    private LocalDateTime toWarrantyAnMon; // Ngày kích hoạt bảo hành ăn mòn + năm kích hoạt từ u_product.bh_an_mon
}
