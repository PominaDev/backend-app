package com.pomina.commonservices.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
    @NotBlank(message = "Coil code is mandatory")
    private String coilCode;

    @PositiveOrZero(message = "Length from must be positive or zero")
    private Integer lengthFrom;

    @PositiveOrZero(message = "Length to must be positive or zero")
    private Integer lengthTo;

    @PositiveOrZero(message = "Length must be positive or zero")
    private Integer length;

    private String name;
    private String coilType;
}
