package com.pomina.webapp.policy_management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentCreateDto;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentRequestDto;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentUpdateDto;
import com.pomina.webapp.policy_management.dto.response.UPolicyParentResponseDto;
import com.pomina.webapp.policy_management.entity.UPolicyParent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UPolicyParentConverter extends BaseConverter<UPolicyParentRequestDto, UPolicyParentResponseDto, UPolicyParent> {

    // Mapping from CreateDto to RequestDto
    UPolicyParentRequestDto toUPolicyParentRequestDto(UPolicyParentCreateDto createDto);

    // Mapping from List<CreateDto> to List<RequestDto>
    List<UPolicyParentRequestDto> fromCreateListToUPolicyParentRequestDtoList(List<UPolicyParentCreateDto> createDtoList);

    // Mapping from UpdateDto to RequestDto
    UPolicyParentRequestDto toUPolicyParentRequestDto(UPolicyParentUpdateDto updateDto);

    // Mapping from List<UpdateDto> to List<RequestDto>
    List<UPolicyParentRequestDto> fromUpdateListToUPolicyParentDtoList(List<UPolicyParentUpdateDto> updateDtoList);


}
