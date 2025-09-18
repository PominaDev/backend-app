package com.pomina.commonservices.excel.entity;

import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarrantyInfoHistoryExport extends WarrantyInfoHistory {
    private String stt;
    private String status;
    private String locationAccountFull;
    private String locationWarrantyFull;
    private String hierarchyLevel;
    private String source;
    private String mau;
    private String doDay;
    private String yearWarrantyAnMon;
    private String yearWarrantyPhaiMau;
}
