package com.pomina.app.role.converter;

import com.pomina.app.role.dto.response.MobileRoleResponeseDto;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleResponeDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MobileRoleConverter {
    List<MobileRoleResponeseDto> toMobileRoleResponeDto (List<SysRoleResponeDto> sysRoleResponeDtos);
}
