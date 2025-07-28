package com.pomina.commonservices.location.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity {

    private Integer locationId;

    private Integer userId;            // FK to SysUser

    private Double latitude;

    private Double longitude;

    private String address01;          // Đường

    private String address02;          // Xã/Phường

    private String address03;          // Huyện/Quận

    private String address04;          // Tỉnh/Thành phố

    private String address05;          // Quốc gia

    private String fullAddress;      // Địa chỉ đầy đủ

    private String countryCode;        // Mã quốc gia

    private Double radius;             // Bán kính
}
