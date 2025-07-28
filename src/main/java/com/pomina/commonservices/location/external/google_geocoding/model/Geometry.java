package com.pomina.commonservices.location.external.google_geocoding.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Geometry {
    private LocationGoogle location;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationGoogle {
        private double lat;
        private double lng;
    }
}
