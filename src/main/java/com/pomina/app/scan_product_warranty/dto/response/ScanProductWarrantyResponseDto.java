package com.pomina.app.scan_product_warranty.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private ProductDetailResponseDto productDetailResponseDto;
}
