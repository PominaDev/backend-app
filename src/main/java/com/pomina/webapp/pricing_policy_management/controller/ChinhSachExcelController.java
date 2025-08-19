package com.pomina.webapp.pricing_policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanTemplateDto;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiConstants.ApiPricingPolicyManagement.BASE)
@RequiredArgsConstructor
public class ChinhSachExcelController {

    private final ChinhSachExcelService chinhSachExcelService;

    @PostMapping(ApiConstants.ApiPricingPolicyManagement.IMPORT_LIST)
    public ResponseEntity<ApiResponse<Integer>> createList(@RequestBody ChinhSachExcelCreateDto chinhSachExcelCreateDto) {
        return ResponseHandler.success(chinhSachExcelService.createList(chinhSachExcelCreateDto));
    }

    @PostMapping(ApiConstants.ApiPricingPolicyManagement.PREVIEW_FILE)
    public ResponseEntity<ApiResponse<ChinhSachGiaBanTemplateDto>> getChinhSachByFile(@RequestParam("file") MultipartFile file) {
        return ResponseHandler.success(chinhSachExcelService.previewChinhSachFromFile(file));
    }
}
