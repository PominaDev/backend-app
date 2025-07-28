package com.pomina.webapp.menu_permission.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuCreateDto;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuUpdateDto;
import com.pomina.webapp.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.webapp.menu_permission.entity.MasterMenu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterMenuConverter extends BaseConverter<MasterMenuRequestDto, MasterMenuResponseDto, MasterMenu> {

    // Mapping from CreateDto to RequestDto
    MasterMenuRequestDto toMasterMenuRequestDto(MasterMenuCreateDto createDto);

    // Mapping from List<CreateDto> to List<RequestDto>
    List<MasterMenuRequestDto> toMasterMenuRequestDtoList(List<MasterMenuCreateDto> createDtoList);

    // Mapping from List<CreateDto> to List<RequestDto>
    List<MasterMenuRequestDto> fromUpdateListToMasterRequestDtoList(List<MasterMenuUpdateDto> updateDtoList);

    // Mapping from UpdateDto to RequestDto
    MasterMenuRequestDto toMasterMenuRequestDto(MasterMenuUpdateDto updateDto);
}
