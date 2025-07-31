package com.pomina.webapp.master_location_managerment.service.impl;

import com.pomina.webapp.master_location_managerment.converter.MasterLocationConverter;
import com.pomina.webapp.master_location_managerment.dto.request.MasterLocationRequest;
import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationRespone;
import com.pomina.webapp.master_location_managerment.mapper.MasterLocationMapper;
import com.pomina.webapp.master_location_managerment.service.MasterLocationManagermentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterLocationManagermentServiceImpl implements MasterLocationManagermentService {
    private final MasterLocationMapper masterLocationMapper;
    private final MasterLocationConverter masterLocationConverter;

    @Override
    public List<MasterLocationRespone> findAll() {
        return masterLocationConverter.toResponseList(masterLocationMapper.findAll());
    }
}
