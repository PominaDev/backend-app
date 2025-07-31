package com.pomina.webapp.user_role_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.user_role_managerment.dto.request.SysRoleRequestDto;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysRoleConverter extends BaseConverter<SysRoleRequestDto, SysRoleResponeDto, SysRole> {

}
