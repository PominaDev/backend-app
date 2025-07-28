package com.pomina.webapp.user_managerment.converter;

import com.pomina.webapp.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.webapp.user_managerment.entity.SysUserRoleWeb;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysUserRoleWebConverter {
    // Mapping from List<Entity> to List<ResponseDto>
    List<SysUserRoleWebResponseDto> toResponseList(List<SysUserRoleWeb> entityList);
}
