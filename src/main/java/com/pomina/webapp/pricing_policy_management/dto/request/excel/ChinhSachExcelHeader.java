package com.pomina.webapp.pricing_policy_management.dto.request.excel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChinhSachExcelHeader {
    private String tenChinhSach;

    private String ngayBatDau;

    private String ngayKetThuc;

    private String phongBan;

    private String nguoiLap;

    private String description;
}
