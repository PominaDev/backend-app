package com.pomina.webapp.product_warranty_activation_tracking.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import com.pomina.webapp.product_warranty_activation_tracking.dto.request.ActivationWarrantyRequestDto;
import com.pomina.webapp.product_warranty_activation_tracking.service.ProductWarrantyActivationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class ProductWarrantyController {

    private final WarrantyService warrantyService;

    private final ProductWarrantyActivationService activationService;

    @GetMapping(ApiConstants.ApiWarranty.GET_WARRANTY_INFO_HISTORY_ALL)
    public ResponseEntity<ApiResponse<PageResponse<WarrantyInfoHistory>>> getAllWarrantyInfoHistory(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(warrantyService.getWarrantyInfoHistory(pageRequest, true));
    }

    @PutMapping(ApiConstants.ApiWarranty.ACTIVATE_WARRANTY)
    public ResponseEntity<ApiResponse<Integer>> activateWarranty(@PathVariable("id") Integer warrantyId, @Valid @RequestBody ActivationWarrantyRequestDto activationWarrantyRequestDto) {
        return ResponseHandler.success(activationService.activateWarranty(warrantyId, activationWarrantyRequestDto));
    }

    @GetMapping(ApiConstants.ApiWarranty.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<WarrantyInfoHistory>>> filterWarrantyInfoHistory(@Valid @ModelAttribute PageRequest pageRequest,
                                                                                                    @RequestParam(required = false) List<String> filter,
                                                                                                    @RequestParam(required = false) String isValid,
                                                                                                    @RequestParam(required = false) String sort) {
        Boolean isValidBool = (isValid == null || isValid.isBlank()) ? null : Boolean.parseBoolean(isValid);
        return ResponseHandler.success(warrantyService.filterWarrantyInfoHistory(pageRequest, true, filter, isValidBool, sort));
    }
}
