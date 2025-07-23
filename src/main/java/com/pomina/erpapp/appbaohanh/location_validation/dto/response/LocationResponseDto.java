package com.pomina.erpapp.appbaohanh.location_validation.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponseDto {

    private Long userId;

    private Double latitude;

    private Double longitude;

    private String road;

    private String city;

    private String country;

    private String countryCode;
}
