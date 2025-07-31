package com.pomina.webapp.master_location_managerment.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MasterLocationResponeDto {
    private String masterLocationCode;
    private String masterLocationName;
    private String description;

    private String status;
    private String noted;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Boolean isDeleted;
}
