package com.pomina.erpapp.appbaohanh.location_validation.entity;

import com.pomina.erpapp.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity {

    private Long id;

    private Long userId;

    private Double latitude;

    private Double longitude;

    private String road;

    private String city;

    private String country;

    private String countryCode;

    private Double radius;
}
