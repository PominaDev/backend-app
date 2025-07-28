package com.pomina.commonservices.location.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckLocationResponse {

    private Boolean isWithinLocation;

    private Double distance; // Khoảng cách tính bằng mét

    private LocationResponseDto registeredLocation;
}
