package com.pomina.erpapp.appbaohanh.web.menu_permission.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuCreateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuUpdateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MasterMenuConverter extends BaseConverter<MasterMenuRequestDto, MasterMenuResponseDto, MasterMenu> {
    // Mapping from CreateDto to RequestDto
    MasterMenuRequestDto toMasterMenuRequestDto(MasterMenuCreateDto createDto);

    // Mapping from UpdateDto to RequestDto
    MasterMenuRequestDto toMasterMenuRequestDto(MasterMenuUpdateDto updateDto);
}
