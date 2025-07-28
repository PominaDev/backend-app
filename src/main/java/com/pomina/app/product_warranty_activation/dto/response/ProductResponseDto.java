package com.pomina.app.product.warranty.activation.dto.response;

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
