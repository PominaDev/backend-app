package com.pomina.commonservices.marketing_pr_chinh_sach.converter;

import com.pomina.common.converter.BaseConverter;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachCreateDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachRequestDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachUpdateDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.response.MarketingPrChinhSachResponseDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.entity.MarketingPrChinhSach;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketingPrChinhSachConverter
        extends BaseConverter<MarketingPrChinhSachRequestDto, MarketingPrChinhSachResponseDto, MarketingPrChinhSach> {

    // Mapping from createDto to requestDto
    MarketingPrChinhSachRequestDto toMarketingPrChinhSachRequestDto(MarketingPrChinhSachCreateDto createDto);

    // Mapping from updateDto to requestDto
    MarketingPrChinhSachRequestDto toMarketingPrChinhSachRequestDto(MarketingPrChinhSachUpdateDto updateDto);
}
