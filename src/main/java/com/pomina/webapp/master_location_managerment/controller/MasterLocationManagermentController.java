package com.pomina.webapp.master_location_managerment.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.master_location_managerment.dto.MasterLocationDto;
import com.pomina.webapp.master_location_managerment.service.MasterLocationManagermentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiMasterLocation.BASE)
@RequiredArgsConstructor
public class MasterLocationManagermentController {
    private final MasterLocationManagermentService masterLocationManagermentService;

    @GetMapping(ApiConstants.ApiMasterLocation.GET_ALL_MASTER_LOCATION)
    public ResponseEntity<ApiResponse<List<MasterLocationDto>>> getAllMasterLocation() {
        return ResponseHandler.success(masterLocationManagermentService.findAll());
    }

}
