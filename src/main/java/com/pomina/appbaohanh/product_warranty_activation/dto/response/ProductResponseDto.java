package com.pomina.appbaohanh.product_warranty_activation.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Integer id;
    private String coilCode;
    private Integer lengthFrom;
    private Integer lengthTo;
    private Integer length;
    private String name;
    private String coilType;
}
