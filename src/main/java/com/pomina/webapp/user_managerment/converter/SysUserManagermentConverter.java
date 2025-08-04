package com.pomina.webapp.user_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.entity.SysUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysUserManagermentConverter extends BaseConverter<SysUserRequestDto, SysUserResponeDto, SysUser> {
}
