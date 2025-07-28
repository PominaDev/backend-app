package com.pomina.commonservices.location.external.google_geocoding.service;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.location.external.google_geocoding.config.GoogleGeocodingConfig;
import com.pomina.commonservices.location.external.google_geocoding.dto.LocationResponse;
import com.pomina.commonservices.location.external.google_geocoding.model.GeocodeResponse;
import com.pomina.commonservices.location.external.google_geocoding.model.GeocodeResult;
import com.pomina.commonservices.location.util.AddressParserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GoogleGeocodingService {

    private final GoogleGeocodingConfig config;

    private final RestTemplate restTemplate;

    public LocationResponse reverseGeocode(double latitude, double longitude) {
        try {
            String url = buildRequestUrl(latitude, longitude);
            GeocodeResponse response = callGoogleApi(url);
            return convertToResponse(response);
        } catch (Exception e) {
            throw new AppException(ErrorCode.GOOGLE_API_ERROR);
        }
    }

    private String buildRequestUrl(double lat, double lng) {
        return UriComponentsBuilder.fromHttpUrl(config.getGeocodeUrl())
                .queryParam("latlng", lat + "," + lng)
                .queryParam("key", config.getApiKey())
                .queryParam("language", "vi")
                .queryParam("region", "vn")
                .toUriString();
    }

    private GeocodeResponse callGoogleApi(String url) {
        ResponseEntity<GeocodeResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                GeocodeResponse.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new AppException(ErrorCode.GOOGLE_API_ERROR);
        }

        GeocodeResponse body = response.getBody();
        if (body != null && !"OK".equals(body.getStatus())) {
            throw new AppException(ErrorCode.GOOGLE_API_ERROR);
        }

        return body;
    }

    private LocationResponse convertToResponse(GeocodeResponse googleResponse) {
        GeocodeResult firstResult = googleResponse.getResults().getFirst();
        String fullAddress = firstResult.getFormattedAddress();

        // Ưu tiên lấy từng field từ component
        String street = AddressParserUtil.getComponent(firstResult, "route");
        String streetNumber = AddressParserUtil.getComponent(firstResult, "street_number");
        String ward = AddressParserUtil.getComponent(firstResult, "sublocality_level_1");
        if (ward == null) {
            ward = AddressParserUtil.getComponent(firstResult, "sublocality");
        }
        String district = AddressParserUtil.getComponent(firstResult, "administrative_area_level_2");
        String city = AddressParserUtil.getComponent(firstResult, "administrative_area_level_1");
        String country = AddressParserUtil.getComponent(firstResult, "country");
        String postalCode = AddressParserUtil.getComponent(firstResult, "postal_code");

        // Nếu thiếu thì fallback từ formatted_address
        if (street == null || streetNumber == null || ward == null || district == null || city == null || country == null) {
            LocationResponse parsed = AddressParserUtil.parseFromFormattedAddress(fullAddress);
            street = street != null ? street : parsed.getStreet();
            streetNumber = streetNumber != null ? streetNumber : parsed.getStreetNumber();
            ward = ward != null ? ward : parsed.getWard();
            district = district != null ? district : parsed.getDistrict();
            city = city != null ? city : parsed.getCity();
            country = country != null ? country : parsed.getCountry();
        }

        return LocationResponse.builder()
                .fullAddress(fullAddress)
                .street(street)
                .streetNumber(streetNumber)
                .ward(ward)
                .district(district)
                .city(city)
                .country(country)
                .postalCode(postalCode)
                .latitude(firstResult.getGeometry().getLocation().getLat())
                .longitude(firstResult.getGeometry().getLocation().getLng())
                .build();
    }
}
