package com.pomina.webapp.grant_approval.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserCreateDto;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserRequestDto;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserUpdateDto;
import com.pomina.webapp.grant_approval.dto.response.MasterGroupUserResponseDto;
import com.pomina.webapp.grant_approval.entity.MasterGroupUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterGroupUserConverter extends BaseConverter<MasterGroupUserRequestDto, MasterGroupUserResponseDto, MasterGroupUser> {

    // Mapping from CreateDto to RequestDto
    MasterGroupUserRequestDto toMasterGroupUserRequestDto(MasterGroupUserCreateDto createDto);

    // Mapping from List<CreateDto> to List<RequestDto>
    List<MasterGroupUserRequestDto> toMasterGroupUserRequestDtoList(List<MasterGroupUserCreateDto> createDtoList);

    // Mapping from List<CreateDto> to List<RequestDto>
    List<MasterGroupUserRequestDto> fromUpdateListToMasterRequestDtoList(List<MasterGroupUserUpdateDto> updateDtoList);

    // Mapping from UpdateDto to RequestDto
    MasterGroupUserRequestDto toMasterGroupUserRequestDto(MasterGroupUserUpdateDto updateDto);
}
