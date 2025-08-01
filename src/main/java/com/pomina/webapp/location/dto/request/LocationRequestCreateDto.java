package com.pomina.webapp.location.dto.request;

import com.pomina.common.model.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationRequestCreateDto extends BaseEntity {
    @NotNull(message = "userId is required")
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
}
