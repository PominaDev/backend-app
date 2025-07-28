package com.pomina.appbaohanh.user_managerment.converter;

import com.pomina.appbaohanh.user_managerment.dto.request.RoleCreateDto;
import com.pomina.appbaohanh.user_managerment.dto.request.RoleRequestDto;
import com.pomina.appbaohanh.user_managerment.dto.request.RoleUpdateDto;
import com.pomina.appbaohanh.user_managerment.dto.response.RoleResponseDto;
import com.pomina.appbaohanh.common.converter.BaseConverter;
import com.pomina.appbaohanh.user_managerment.entity.SysRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseConverter<RoleRequestDto, RoleResponseDto, SysRole> {

    // Map from RoleCreateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleCreateDto roleCreateDto);

    // Map from RoleUpdateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleUpdateDto roleUpdateDto);
}
