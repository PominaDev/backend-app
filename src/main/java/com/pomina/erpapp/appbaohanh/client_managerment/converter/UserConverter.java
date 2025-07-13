package com.pomina.erpapp.appbaohanh.client_managerment.converter;

import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserCreateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserUpdateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.UserResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.entity.SysUser;
import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<UserRequestDto, UserResponseDto, SysUser> {

    @Override
    @Mapping(target = "fullAddress", expression = "java(buildFullAddress(entity))")
    @Mapping(source = "role.roleName", target = "roleName")
    UserResponseDto toResponse(SysUser entity);

    @Override
    List<UserResponseDto> toResponseList(List<SysUser> entityList);

    @Override
    @Mapping(target = "role", ignore = true)
    SysUser toEntity(UserRequestDto dto);

    @Override
    List<SysUser> toEntityList(List<UserRequestDto> dtoList);

    // CreateDto to RequestDto
    UserRequestDto toUserRequestDto(UserCreateDto createDto);

    // UpdateDto to RequestDto
    UserRequestDto toUserRequestDto(UserUpdateDto updateDto);

    default String buildFullAddress(SysUser entity) {
        return Stream.of(
                entity.getAddress1(),
                entity.getAddress2(),
                entity.getAddress3(),
                entity.getAddress4(),
                entity.getAddress5()
            )
            .filter(addr -> addr != null && !addr.isBlank())
            .reduce((a, b) -> a + ", " + b)
            .orElse("");
    }
}