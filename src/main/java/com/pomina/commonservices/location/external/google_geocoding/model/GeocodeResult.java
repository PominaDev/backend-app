package com.pomina.servicecommon.location.external.google_geocoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeocodeResult {

    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    private Geometry geometry;
}
