package com.pomina.webapp.product_warranty_activation_tracking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationWarrantyRequestDto {

    private Boolean isValid;

    private String status;

    private String noted;
}
