package com.pomina.webapp.master_location_managerment.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.webapp.master_location_managerment.dto.request.MasterLocationRequest;
import com.pomina.webapp.master_location_managerment.dto.respone.MasterLocationRespone;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterLocationConverter extends BaseConverter<MasterLocationRequest, MasterLocationRespone, MasterLocation> {
}
