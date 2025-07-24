package com.pomina.erpapp.appbaohanh.web.menu_permission.service;

import com.pomina.erpapp.appbaohanh.common.service.BaseService;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;

import java.util.List;

public interface MasterMenuService extends BaseService<MasterMenuRequestDto, MasterMenuResponseDto, Integer> {
    List<MasterMenuResponseDto> getAll();
}
