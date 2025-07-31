package com.pomina.webapp.master_location_managerment.service;

import com.pomina.webapp.master_location_managerment.dto.request.MasterLocationRequest;
import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationRespone;

import java.util.List;

public interface MasterLocationManagermentService {
    List<MasterLocationRespone> findAll();

}
