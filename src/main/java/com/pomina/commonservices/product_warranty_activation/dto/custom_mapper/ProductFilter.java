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

    private List<String> doMa;

    private String doDay;

    private List<String> mauSac;

    private List<String> macThep;

    private String khoRong;

    private String textSearch;
}
