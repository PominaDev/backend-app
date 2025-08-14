package com.pomina.webapp.pricing_policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachParentResponseDto;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiPolicyManagement.BASE)
@RequiredArgsConstructor
public class ChinhSachParentController extends BaseController<ChinhSachParentCreateDto, ChinhSachParentUpdateDto, ChinhSachParentResponseDto, Integer> {

    private final ChinhSachParentService chinhSachParentService;

    @Override
    public ResponseEntity<ApiResponse<Integer>> create(ChinhSachParentCreateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<ChinhSachParentResponseDto>> getById(Integer id) {
        return null;
    }

    @GetMapping(ApiConstants.ApiPolicyManagement.GET_ALL)
    @Override
    public ResponseEntity<ApiResponse<PageResponse<ChinhSachParentResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(chinhSachParentService.search(pageRequest));
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> update(Integer id, ChinhSachParentUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Integer>> delete(Integer id) {
        return null;
    }
}
