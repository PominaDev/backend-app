package com.pomina.webapp.pricing_policy_management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.pricing_policy_management.dto.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.excel.Header;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachParentResponseDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachParent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ChinhSachParentConverter extends BaseConverter<ChinhSachParentRequestDto, ChinhSachParentResponseDto, ChinhSachParent> {

    // Mapping from createDto to RequestDto
    ChinhSachParentRequestDto toChinhSachParentRequestDto(ChinhSachParentCreateDto createDto);

    // Mapping from updateDto to RequestDto
    ChinhSachParentRequestDto toChinhSachParentRequestDto(ChinhSachParentUpdateDto updateDto);

    @Mapping(target = "uChinhSachParentName", source = "tenChinhSach")
    @Mapping(target = "deptUpload", source = "phongBan")
    @Mapping(target = "userUpload", source = "nguoiLap")
    @Mapping(target = "uDayBegin", source = "ngayBatDau", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "uDayEnd", source = "ngayKetThuc", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "uDescription", source = "description")
    ChinhSachParent toChinhSachParent(Header header);

    @Named("toLocalDateTime")
    default LocalDateTime map(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy][dd/MM/yyyy]");
        return LocalDate.parse(date, formatter).atStartOfDay();
    }
}
