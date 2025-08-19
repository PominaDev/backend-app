package com.pomina.commonservices.product_warranty_activation.dto.custom_mapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilter {

    private String loaiTon;

    private String loaiHang;

    private String beMat;

    private List<String> doMaList;

    private List<String> doDayList;

    private List<String> mauSacList;

    private List<String> macThepList;
}
