package com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerResponseDto {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String address;
    private String status;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
