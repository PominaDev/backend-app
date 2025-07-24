package com.pomina.erpapp.appbaohanh.web.menu_permission.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionCreateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterPermissionUpdateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterPermission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MasterPermissionConverter extends BaseConverter<MasterPermissionRequestDto, MasterPermissionResponseDto, MasterPermission> {
    // Mapping from MasterPermissionCreateDto to MasterPermissionRequestDto
    MasterPermissionRequestDto toMasterPermissionRequestDto(MasterPermissionCreateDto createDto);

    // Mapping from MasterPermissionUpdateDto to MasterPermissionRequestDto
    MasterPermissionRequestDto toMasterPermissionRequestDto(MasterPermissionUpdateDto updateDto);
}
