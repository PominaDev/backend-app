package com.pomina.servicecommon.location.external.google_geocoding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {

    private String fullAddress;

    private String street;

    private String streetNumber;

    private String ward;

    private String district;

    private String city;

    private String country;

    private String postalCode;

    private double latitude;

    private double longitude;
}
