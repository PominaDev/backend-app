package com.pomina.webapp.master_location_managerment.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterLocation extends BaseEntity {
    private String masterLocationCode;
    private String masterLocationName;
    private String description;
}
