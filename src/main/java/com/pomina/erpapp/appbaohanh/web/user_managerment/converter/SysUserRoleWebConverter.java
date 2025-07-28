package com.pomina.erpapp.appbaohanh.web.user_managerment.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.request.SysUserRoleWebRequest;
import com.pomina.erpapp.appbaohanh.web.user_managerment.dto.respone.SysUserRoleWebResponseDto;
import com.pomina.erpapp.appbaohanh.web.user_managerment.entity.SysUserRoleWeb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysUserRoleWebConverter extends BaseConverter<SysUserRoleWebRequest, SysUserRoleWebResponseDto, SysUserRoleWeb> {
}
