package com.pomina.commonservices.product_warranty_activation.dto.custom_mapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilter {

    private List<String> loaiTon;

    private List<String> loaiHang;

    private List<String> beMat;

    private List<String> doMa;

    private List<String> doDay;

    private List<String> mauSac;

    private List<String> macThep;

    private List<String> khoRong;

    private String textSearch;
}
