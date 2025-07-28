package com.pomina.appbaohanh.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    @NotBlank(message = "Coil code is mandatory")
    private String coilCode;

    private Integer lengthFrom;
    private Integer lengthTo;
    private Integer length;
    private String name;
    private String coilType;
}
