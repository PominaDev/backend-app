package com.pomina.servicecommon.location.external.nominatim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetails {

    private String road;

    private String city;

    private String country;

    @JsonProperty("country_code")
    private String countryCode;
}
