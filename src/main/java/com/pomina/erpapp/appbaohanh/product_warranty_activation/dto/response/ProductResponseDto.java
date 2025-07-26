package com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String status;
    private String noted;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
