package com.pomina.webapp.pricing_policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanChildResponseDto;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachGiaBanChildService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiPricingPolicyManagement.BASE)
@RequiredArgsConstructor
public class ChinhSachGiaBanChildController extends BaseController<ChinhSachGiaBanChildCreateDto, ChinhSachGiaBanChildUpdateDto, ChinhSachGiaBanChildResponseDto, Integer> {

    private final ChinhSachGiaBanChildService chinhSachGiaBanChildService;

    @Override
    public ResponseEntity<ApiResponse<Integer>> create(ChinhSachGiaBanChildCreateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<ChinhSachGiaBanChildResponseDto>> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<PageResponse<ChinhSachGiaBanChildResponseDto>>> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> update(Integer id, ChinhSachGiaBanChildUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> delete(Integer id) {
        return null;
    }

    @GetMapping("{uChinhSachParentId}")
    public ResponseEntity<ApiResponse<PageResponse<ChinhSachGiaBanChildResponseDto>>> getByUChinhSachParentId(@Valid @ModelAttribute PageRequest pageRequest,
                                                                                                              @PathVariable("uChinhSachParentId") int uChinhSachParentId) {
        return ResponseHandler.success(chinhSachGiaBanChildService.getByUChinhSachParentId(pageRequest, uChinhSachParentId));
    }
}
