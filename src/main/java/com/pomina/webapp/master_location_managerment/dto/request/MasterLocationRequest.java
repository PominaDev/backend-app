package com.pomina.webapp.master_location_managerment.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterLocationRequest {
    private String masterLocationCode;
    private String masterLocationName;
    private String description;
}
