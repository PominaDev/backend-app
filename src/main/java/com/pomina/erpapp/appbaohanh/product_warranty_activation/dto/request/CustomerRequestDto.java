package com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotNull(message = "Customers name is required")
    private String name;

    private String phoneNumber;

    private String address;
}

