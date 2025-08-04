package com.pomina.webapp.master_location_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationResponeDto;
import com.pomina.webapp.master_location_managerment.service.MasterLocationManagermentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiMasterLocation.BASE)
@RequiredArgsConstructor
public class MasterLocationManagermentController {
    private final MasterLocationManagermentService masterLocationManagermentService;

    @GetMapping(ApiConstants.ApiMasterLocation.GET_ALL_MASTER_LOCATION)
    public ResponseEntity<ApiResponse<List<MasterLocationResponeDto>>> getAllMasterLocation() {
        return ResponseHandler.success(masterLocationManagermentService.findAll());
    }

}
