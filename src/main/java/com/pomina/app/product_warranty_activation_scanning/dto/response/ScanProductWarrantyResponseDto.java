package com.pomina.app.product_warranty_activation_scanning.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScanProductWarrantyResponseDto {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Integer resultInserted;

    private WarrantyInfoHistory productDetailResponseDto;

    private String message;
}
