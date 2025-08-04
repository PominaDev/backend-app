package com.pomina.webapp.product_warranty_activation_tracking.service;

import com.pomina.webapp.product_warranty_activation_tracking.dto.request.ActivationWarrantyRequestDto;

public interface ProductWarrantyActivationService {

    Integer activateWarranty(Integer warrantyId, ActivationWarrantyRequestDto activationWarrantyRequestDto);
}
