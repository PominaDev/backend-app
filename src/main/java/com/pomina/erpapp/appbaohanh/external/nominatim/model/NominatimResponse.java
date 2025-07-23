package com.pomina.erpapp.appbaohanh.external.nominatim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NominatimResponse {

    @JsonProperty("place_id")
    private String placeId;

    private String lat;

    private String lon;

    @JsonProperty("display_name")
    private String displayName;

    private AddressDetails address;
}
