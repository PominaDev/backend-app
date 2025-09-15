package com.pomina.webapp.pricing_policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.pricing_policy_management.converter.ChinhSachParentConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentUpdateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachParentResponseDto;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiPolicyManagement.BASE)
@RequiredArgsConstructor
public class ChinhSachParentController extends BaseController<ChinhSachParentCreateDto, ChinhSachParentUpdateDto, ChinhSachParentResponseDto, Integer> {

    // Dependency injection
    private final ChinhSachParentService chinhSachParentService;
    private final ChinhSachParentConverter chinhSachParentConverter;

    @Override
    @PostMapping(ApiConstants.ApiPolicyManagement.CREATE)
    public ResponseEntity<ApiResponse<Integer>> create(@RequestBody ChinhSachParentCreateDto dto) {
        // Convert form creatDto to requestDto
        ChinhSachParentRequestDto requestDto = chinhSachParentConverter.toChinhSachParentRequestDto(dto);
        return ResponseHandler.success(chinhSachParentService.create(requestDto));
    }

    @Override
    @GetMapping(ApiConstants.ApiPolicyManagement.GET_BY_ID)
    public ResponseEntity<ApiResponse<ChinhSachParentResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(chinhSachParentService.getById(id));
    }

    @GetMapping(ApiConstants.ApiPolicyManagement.GET_ALL)
    @Override
    public ResponseEntity<ApiResponse<PageResponse<ChinhSachParentResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(chinhSachParentService.search(pageRequest));
    }

    @Override
    @PutMapping(ApiConstants.ApiPolicyManagement.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody ChinhSachParentUpdateDto dto) {
        // convert from updateDto to requestDto
        ChinhSachParentRequestDto requestDto = chinhSachParentConverter.toChinhSachParentRequestDto(dto);
        return ResponseHandler.success(chinhSachParentService.update(id, requestDto));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiPolicyManagement.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(chinhSachParentService.delete(id));
    }
}
