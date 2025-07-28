package com.pomina.webapp.menu_permission.service;

import com.pomina.common.service.BaseService;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.webapp.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.webapp.menu_permission.dto.response.MenuStructured;

import java.util.List;

public interface MasterMenuService extends BaseService<MasterMenuRequestDto, MasterMenuResponseDto, Integer> {
    Integer createListMasterMenu(List<MasterMenuRequestDto> dtoList);

    List<MasterMenuResponseDto> getAll();

    List<MenuStructured> getMenuPermissionByUserId(Integer userId);

    Integer updateListMasterMenu(List<MasterMenuRequestDto> dtoList);
}
