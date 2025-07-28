package com.pomina.commonservices.location.external.google_geocoding.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.maps")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleGeocodingConfig {

    @Value("${google.maps.api-key}")
    private String apiKey;

    @Value("${google.maps.geocoding-url}")
    private String geocodeUrl;
}
