package com.pomina.erpapp.appbaohanh.product_warranty_activation.entity;

import com.pomina.erpapp.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activation extends BaseEntity {

    private Long id;

    private String code;

    private LocalDate activationDate;

    private LocalDate colorFadeWarrantyExpiry;

    private LocalDate corrosionWarrantyExpiry;

    // FOREIGN KEY
    private Long customerId;

    private Long productId;

    private Long distributorId;
}
