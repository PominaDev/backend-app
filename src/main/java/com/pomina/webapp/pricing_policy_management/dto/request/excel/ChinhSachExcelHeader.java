package com.pomina.webapp.pricing_policy_management.dto.request.excel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChinhSachExcelHeader {
    @JsonProperty("ten_chinh_sach")
    private String tenChinhSach;

    @JsonProperty("ngay_bat_dau")
    private String ngayBatDau;

    @JsonProperty("ngay_ket_thuc")
    private String ngayKetThuc;

    @JsonProperty("phong_ban")
    private String phongBan;

    @JsonProperty("nguoi_lap")
    private String nguoiLap;

    @JsonProperty("u_description")
    private String description;
}
