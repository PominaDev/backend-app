package com.pomina.commonservices.user.management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.user.management.dto.request.UserCreateDto;
import com.pomina.commonservices.user.management.dto.request.UserRequestDto;
import com.pomina.commonservices.user.management.dto.request.UserUpdateDto;
import com.pomina.commonservices.user.management.dto.response.UserResponseDto;
import com.pomina.commonservices.user.management.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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