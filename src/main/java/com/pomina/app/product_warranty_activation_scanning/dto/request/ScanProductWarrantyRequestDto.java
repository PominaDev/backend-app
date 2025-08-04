package com.pomina.app.product_warranty_activation_scanning.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScanProductWarrantyRequestDto {

    @NotBlank
    private String maCuonTon;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
