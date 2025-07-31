package com.pomina.webapp.master_location_managerment.service;

import com.pomina.webapp.master_location_managerment.dto.MasterLocationDto;

import java.util.List;

public interface MasterLocationManagermentService {
    List<MasterLocationDto> findAll();

}
