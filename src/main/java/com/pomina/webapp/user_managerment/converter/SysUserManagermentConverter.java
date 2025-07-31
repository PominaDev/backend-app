package com.pomina.webapp.user_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.entity.SysUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysUserManagermentConverter extends BaseConverter<SysUserRequestDto, SysUserResponeDto, SysUser> {

    @Override
    SysUser toEntity(SysUserRequestDto requestDto);

    @Override
    List<SysUser> toEntityList(List<SysUserRequestDto> requestDtoList);

    @Override
    SysUserResponeDto toResponse(SysUser entity);

    @Override
    List<SysUserResponeDto> toResponseList(List<SysUser> entityList);
}
