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
public class GeocodeResponse {

    private List<GeocodeResult> results;

    private String status; // "OK" | "ZERO_RESULTS" | "ERROR"

    @JsonProperty("error_message")
    private String errorMessage;
}
