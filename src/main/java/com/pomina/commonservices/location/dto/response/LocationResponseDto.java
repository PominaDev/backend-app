package com.pomina.commonservices.location.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponseDto {

    private Integer userId;

    private Double latitude;

    private Double longitude;

    private String road;

    private String ward;

    private String district;

    private String city;

    private String country;

    private String countryCode;

    private String fullAddress;
}
