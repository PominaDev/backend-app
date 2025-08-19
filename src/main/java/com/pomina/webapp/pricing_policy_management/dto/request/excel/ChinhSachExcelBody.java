package com.pomina.webapp.pricing_policy_management.dto.request.excel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ChinhSachExcelBody {
    private String tenSanPham;

    private Double size;

    private Double giaBang;

    private Double ckThanhToan;

    private Double csTrongThang;

    private Double ckCs1;

    private Double ckCs2;

    private Double ckCs3;

    private Double ckCs4;

    private Double ckCs5;

    private Double ckCs6;

    private Double ckCs7;

    private Double ckCs8;

    private Double ckCs9;

    private Double ckCs10;

    private Double giaMet;

    @JsonIgnore
    private List<String> warnings;

    public void addWarning(String warning) {
        if (this.warnings == null) {
            this.warnings = new ArrayList<>();
        }
        this.warnings.add(warning);
    }
}
