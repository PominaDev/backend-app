package com.pomina.app.product.warranty.activation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String coilCode;

    private Integer lengthFrom;

    private Integer lengthTo;

    private Integer length;

    private String name;

    private String coilType;
}
