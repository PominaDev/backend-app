package com.pomina.servicecommon.location.external.nominatim.client;

import com.pomina.servicecommon.location.external.nominatim.model.NominatimResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NominatimClient {

    private final RestTemplate restTemplate;

    @Value("${nominatim.url}")
    protected String nominatimApiUrl;

    @Value("${nominatim.email}")
    protected String nominatimEmail;

    public NominatimResponse reverseGeocode(Double latitude, Double longitude) {

        if (latitude == null || longitude == null) {
            return null;
        }

        nominatimApiUrl = nominatimApiUrl + "/reverse";

        // Build Nominatim API URL
        String url = UriComponentsBuilder.fromHttpUrl(nominatimApiUrl)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("format", "json")
                .queryParam("addressdetails", 1)
                .queryParam("accept-language", "vi")
                .queryParam("email", nominatimEmail)
                .toUriString();

        // Call Nominatim API
        ResponseEntity<NominatimResponse> response = restTemplate.getForEntity(url, NominatimResponse.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch address from Nominatim API");
        }

        return response.getBody();
    }
}
