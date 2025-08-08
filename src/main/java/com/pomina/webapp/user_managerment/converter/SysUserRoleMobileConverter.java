package com.pomina.webapp.user_managerment.converter;

import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleMobileResponseDto;
import com.pomina.webapp.user_managerment.entity.SysUserRoleMobile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysUserRoleMobileConverter {
    List<SysUserRoleMobileResponseDto> toResponseList(List<SysUserRoleMobile> entityList);

}
