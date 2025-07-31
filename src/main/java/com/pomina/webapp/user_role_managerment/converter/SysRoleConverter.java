package com.pomina.webapp.user_role_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.user_role_managerment.dto.request.SysRoleRequest;
import com.pomina.webapp.user_role_managerment.dto.respone.SysRoleRespone;
import com.pomina.webapp.user_role_managerment.entity.SysRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysRoleConverter extends BaseConverter<SysRoleRequest, SysRoleRespone, SysRole> {

}
