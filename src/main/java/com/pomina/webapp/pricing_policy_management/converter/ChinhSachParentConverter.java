package com.pomina.webapp.pricing_policy_management.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelHeader;
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

    // Mapping from requestDto  to Entity
    @Mapping(target = "uChinhSachParentId", source = "chinhSachParentId")
    @Mapping(target = "uChinhSachParentType", source = "chinhSachParentType")
    @Mapping(target = "uChinhSachParentCode", source = "chinhSachParentCode")
    @Mapping(target = "uChinhSachParentName", source = "chinhSachParentName")
    @Mapping(target = "uDayBegin", source = "dayBegin")
    @Mapping(target = "uDayEnd", source = "dayEnd")
    @Mapping(target = "uStatus", source = "statusU")
    @Mapping(target = "uDescription", source = "description")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "masterGroupUserCode", source = "masterGroupUserCode")
    @Mapping(target = "dateUpload", source = "dateUpload")
    @Mapping(target = "deptUpload", source = "deptUpload")
    @Mapping(target = "userUpload", source = "userUpload")
    @Mapping(target = "urlGoogledriv", source = "urlGoogledriv")
    @Mapping(target = "urlYoutube", source = "urlYoutube")
    ChinhSachParent toEntity(ChinhSachParentRequestDto requestDto);

    // Mapping from entity to responseDto
    ChinhSachParentResponseDto toResponse(ChinhSachParent entity);

    @Mapping(target = "uChinhSachParentName", source = "tenChinhSach")
    @Mapping(target = "deptUpload", source = "phongBan")
    @Mapping(target = "userUpload", source = "nguoiLap")
    @Mapping(target = "uDayBegin", source = "ngayBatDau", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "uDayEnd", source = "ngayKetThuc", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "uDescription", source = "description")
    ChinhSachParent toChinhSachParent(ChinhSachExcelHeader chinhSachExcelHeader);

    @Named("toLocalDateTime")
    default LocalDateTime map(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy][dd/MM/yyyy]");
        return LocalDate.parse(date, formatter).atStartOfDay();
    }
}
