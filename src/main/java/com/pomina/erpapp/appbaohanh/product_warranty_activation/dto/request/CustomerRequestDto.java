package com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String phoneNumber;
    private String address;
    private String status;
    private String note;
    private String createdBy;
    private String updatedBy;
}

