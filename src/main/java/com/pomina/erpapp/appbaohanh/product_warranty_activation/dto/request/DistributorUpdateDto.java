package com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributorUpdateDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
