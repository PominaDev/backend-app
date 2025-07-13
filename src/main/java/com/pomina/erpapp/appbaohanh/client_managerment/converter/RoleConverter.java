package com.pomina.erpapp.appbaohanh.client_managerment.converter;

import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleCreateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleUpdateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.RoleResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.entity.SysRole;
import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseConverter<RoleRequestDto, RoleResponseDto, SysRole> {

    // Map from RoleCreateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleCreateDto roleCreateDto);

    // Map from RoleUpdateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleUpdateDto roleUpdateDto);
}
