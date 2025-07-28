package com.pomina.commonservices.user.management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.user.management.dto.request.RoleCreateDto;
import com.pomina.commonservices.user.management.dto.request.RoleRequestDto;
import com.pomina.commonservices.user.management.dto.request.RoleUpdateDto;
import com.pomina.commonservices.user.management.dto.response.RoleResponseDto;
import com.pomina.commonservices.user.management.entity.SysRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseConverter<RoleRequestDto, RoleResponseDto, SysRole> {

    // Map from RoleCreateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleCreateDto roleCreateDto);

    // Map from RoleUpdateDto to RoleRequestDto
    RoleRequestDto toRoleRequestDto(RoleUpdateDto roleUpdateDto);
}
