package com.pomina.webapp.location.dto.respones;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LocationResponseDto {

    private Integer uLocationId;
    private Integer userId;
    private Double latitude;
    private Double longitude;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;
    private String countryCode;
    private Double radius;
    private String road;
    private String status;
    private String noted;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
