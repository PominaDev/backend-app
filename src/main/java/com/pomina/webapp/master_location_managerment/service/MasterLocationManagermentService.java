package com.pomina.webapp.master_location_managerment.service;

import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationResponeDto;

import java.util.List;

public interface MasterLocationManagermentService {
    List<MasterLocationResponeDto> findAll();

}
