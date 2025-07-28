package com.pomina.appbaohanh.location.util;

import com.pomina.appbaohanh.location.external.google_geocoding.dto.LocationResponse;
import com.pomina.appbaohanh.location.external.google_geocoding.model.AddressComponent;
import com.pomina.appbaohanh.location.external.google_geocoding.model.GeocodeResult;

import java.util.Arrays;

public class AddressParserUtil {

    private AddressParserUtil() {
    }

    public static String getComponent(GeocodeResult result, String type) {
        return result.getAddressComponents().stream().filter(component -> component.getTypes().contains(type)).findFirst().map(AddressComponent::getLongName).orElse(null);
    }

    public static LocationResponse parseFromFormattedAddress(String fullAddress) {
        String[] parts = fullAddress.split(",\\s*");
        int len = parts.length;

        String country = len >= 1 ? parts[len - 1] : null;
        String city = len >= 2 ? parts[len - 2] : null;
        String district = findDistrict(parts);
        String ward = findWard(parts);
        String street = findStreet(parts);
        String streetNumber = findStreetNumber(parts);

        return LocationResponse.builder().fullAddress(fullAddress).streetNumber(streetNumber).street(street).ward(ward).district(district).city(city).country(country).build();
    }

    private static String findDistrict(String[] parts) {
        return Arrays.stream(parts)
                .filter(p -> p
                        .trim()
                        .matches("(?i).*(quận|huyện|district).*"))
                .findFirst()
                .map(String::trim)
                .orElse(null);
    }

    private static String findWard(String[] parts) {
        // Ưu tiên tìm phần có từ khóa rõ ràng
        for (String part : parts) {
            if (part.trim().matches("(?i).*(phường|xã|ward|sublocality).*")) {
                return part.trim();
            }
        }

        // Nếu không có từ khóa, thử lấy phần đứng trước "quận"
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].trim().toLowerCase().contains("quận")) {
                return parts[i - 1].trim(); // phần tử trước "quận" có thể là phường
            }
        }

        return null;
    }

    private static String findStreet(String[] parts) {
        return Arrays.stream(parts).filter(p -> p.trim().matches("(?i).*(đường|street|road|avenue|lane).*")).findFirst().map(String::trim).orElse(null);
    }

    private static String findStreetNumber(String[] parts) {
        // Ưu tiên phần đầu tiên: thường chứa số nhà (ví dụ: "22 Lot A4")
        return parts.length > 0 ? parts[0].trim() : null;
    }
}