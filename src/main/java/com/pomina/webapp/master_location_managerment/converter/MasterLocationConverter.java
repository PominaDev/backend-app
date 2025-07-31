package com.pomina.webapp.master_location_managerment.converter;

import com.pomina.webapp.master_location_managerment.dto.MasterLocationDto;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import com.pomina.webapp.user_managerment.entity.SysUserRoleWeb;
import lombok.Data;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterLocationConverter {
    List<MasterLocationDto> toResponseList(List<MasterLocation> entityList);
}
