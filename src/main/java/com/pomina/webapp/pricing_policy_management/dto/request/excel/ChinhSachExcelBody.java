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
    @JsonProperty("ten_san_pham")
    private String tenSanPham;

    @JsonProperty("size")
    private Double size;

    @JsonProperty("gia_bang")
    private Double giaBang;

    @JsonProperty("ck_thanh_toan")
    private Double ckThanhToan;

    @JsonProperty("cs_trong_thang")
    private Double csTrongThang;

    @JsonProperty("ck_cs_1")
    private Double ckCs1;

    @JsonProperty("ck_cs_2")
    private Double ckCs2;

    @JsonProperty("ck_cs_3")
    private Double ckCs3;

    @JsonProperty("ck_cs_4")
    private Double ckCs4;

    @JsonProperty("ck_cs_5")
    private Double ckCs5;

    @JsonProperty("ck_cs_6")
    private Double ckCs6;

    @JsonProperty("ck_cs_7")
    private Double ckCs7;

    @JsonProperty("ck_cs_8")
    private Double ckCs8;

    @JsonProperty("ck_cs_9")
    private Double ckCs9;

    @JsonProperty("ck_cs_10")
    private Double ckCs10;

    @JsonProperty("gia_met")
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
