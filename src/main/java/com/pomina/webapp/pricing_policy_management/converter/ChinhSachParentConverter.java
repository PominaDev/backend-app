package com.pomina.webapp.pricing_policy_management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachParentResponseDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachParent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChinhSachParentConverter extends BaseConverter<ChinhSachParentRequestDto, ChinhSachParentResponseDto, ChinhSachParent> {

    // Mapping from createDto to RequestDto
    ChinhSachParentRequestDto toChinhSachParentRequestDto(ChinhSachParentCreateDto createDto);

    // Mapping from updateDto to RequestDto
    ChinhSachParentRequestDto toChinhSachParentRequestDto(ChinhSachParentUpdateDto updateDto);
}
