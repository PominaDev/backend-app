package com.pomina.webapp.user_role_managerment.converter;

import com.pomina.webapp.master_location_managerment.dto.MasterLocationDto;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import com.pomina.webapp.user_role_managerment.dto.SysRoleDto;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysRoleConverter {
    List<SysRoleDto> toResponseList(List<SysRole> sysRoleList);

}
