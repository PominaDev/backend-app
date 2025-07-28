package com.pomina.appbaohanh.user_managerment.converter;

import com.pomina.appbaohanh.user_managerment.dto.request.UserCreateDto;
import com.pomina.appbaohanh.user_managerment.dto.request.UserRequestDto;
import com.pomina.appbaohanh.user_managerment.dto.request.UserUpdateDto;
import com.pomina.appbaohanh.user_managerment.dto.response.UserResponseDto;
import com.pomina.appbaohanh.common.converter.BaseConverter;
import com.pomina.appbaohanh.user_managerment.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<UserRequestDto, UserResponseDto, SysUser> {

    @Override
    @Mapping(target = "fullName", source = "hoVaTen")
    @Mapping(target = "taxCode", source = "maSoThue")
    UserResponseDto toResponse(SysUser entity);

    @Override
    @Mapping(target = "fullName", source = "hoVaTen")
    @Mapping(target = "taxCode", source = "maSoThue")
    List<UserResponseDto> toResponseList(List<SysUser> entityList);

    @Override
    SysUser toEntity(UserRequestDto dto);

    @Override
    List<SysUser> toEntityList(List<UserRequestDto> dtoList);

    // CreateDto to RequestDto
    UserRequestDto toUserRequestDto(UserCreateDto createDto);

    // UpdateDto to RequestDto
    UserRequestDto toUserRequestDto(UserUpdateDto updateDto);
}