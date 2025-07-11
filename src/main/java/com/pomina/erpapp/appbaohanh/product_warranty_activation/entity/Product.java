package com.pomina.erpapp.appbaohanh.product_warranty_activation.entity;

import com.pomina.erpapp.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    private Long id;

    private String coilCode;

    private Integer lengthFrom;

    private Integer lengthTo;

    private Integer length;

    private String name;

    private String coilType;
}
