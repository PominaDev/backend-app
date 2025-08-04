package com.pomina.webapp.master_location_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.master_location_managerment.dto.request.MasterLocationRequestDto;
import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationResponeDto;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MasterLocationConverter extends BaseConverter<MasterLocationRequestDto, MasterLocationResponeDto, MasterLocation> {
}
