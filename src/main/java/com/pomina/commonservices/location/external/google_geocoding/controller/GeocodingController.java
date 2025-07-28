package com.pomina.commonservices.location.external.google_geocoding.controller;

import com.pomina.commonservices.location.external.google_geocoding.dto.LocationResponse;
import com.pomina.commonservices.location.external.google_geocoding.service.GoogleGeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geocoding")
@RequiredArgsConstructor

public class GeocodingController {
    private final GoogleGeocodingService geocodingService;

    @GetMapping("/reverse")
    public LocationResponse reverseGeocode(
            @RequestParam double lat,
            @RequestParam double lng) {
        return geocodingService.reverseGeocode(lat, lng);
    }
}
