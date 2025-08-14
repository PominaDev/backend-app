package com.pomina.webapp.pricing_policy_management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.pricing_policy_management.dto.excel.Body;
import com.pomina.webapp.pricing_policy_management.dto.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanChildResponseDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachGiaBanChild;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChinhSachGiaBanChildConverter extends BaseConverter<ChinhSachGiaBanChildRequestDto, ChinhSachGiaBanChildResponseDto, ChinhSachGiaBanChild> {
    // Mapping from createDto to RequestDto
    ChinhSachGiaBanChildRequestDto toChinhSachGiaBanChildRequestDto(ChinhSachGiaBanChildCreateDto createDto);

    // Mapping from updateDto to RequestDto
    ChinhSachGiaBanChildRequestDto toChinhSachGiaBanChildRequestDto(ChinhSachGiaBanChildUpdateDto updateDto);

    // Mapping from List<Body> to List<ChinhSachGiaBanChild>
    List<ChinhSachGiaBanChild> toListChinhSachGiaBanChild(List<Body> body);
}
