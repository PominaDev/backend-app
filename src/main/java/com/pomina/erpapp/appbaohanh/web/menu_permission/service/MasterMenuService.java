package com.pomina.erpapp.appbaohanh.web.menu_permission.service;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;

import java.util.List;

public interface MasterMenuService {
    int create(List<MasterMenuRequestDto> requestDto);
    MasterMenuResponseDto getById(Integer id);
    List<MasterMenuResponseDto> getAll();
    int update(List<MasterMenuRequestDto> requestDto);
    int delete(List<Integer> id);
    PageResponse<MasterMenuResponseDto> search(PageRequest pageRequest);



}
