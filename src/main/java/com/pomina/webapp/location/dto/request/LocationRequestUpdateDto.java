package com.pomina.webapp.location.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationRequestUpdateDto {
    private Integer userId;
    @Size(max = 255)
    private String address01;
    @Size(max = 255)
    private String address02;
    @Size(max = 255)
    private String address03;
    @Size(max = 255)
    private String address04;
    @Size(max = 255)
    private String address05;
    @Size(max = 255)
    private String fullAddress;
    @Size(max = 255)
    private String countryCode;


    private String status;
    private String noted;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
