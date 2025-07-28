package com.pomina.commonservices.user_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.user_management.converter.RoleConverter;
import com.pomina.commonservices.user_management.dto.request.RoleCreateDto;
import com.pomina.commonservices.user_management.dto.request.RoleRequestDto;
import com.pomina.commonservices.user_management.dto.request.RoleUpdateDto;
import com.pomina.commonservices.user_management.dto.response.RoleResponseDto;
import com.pomina.commonservices.user_management.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiRoleManager.BASE)
@RequiredArgsConstructor
public class RoleController extends BaseController<RoleCreateDto, RoleUpdateDto, RoleResponseDto, Integer> {

    // Dependency injection
    private final RoleService roleService;
    private final RoleConverter roleConverter;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> create(RoleCreateDto dto) {
        RoleRequestDto requestDto = roleConverter.toRoleRequestDto(dto);
        return ResponseHandler.success(roleService.create(requestDto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(roleService.getById(id));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<RoleResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(roleService.search(pageRequest));
    }

    @Override
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody RoleUpdateDto dto) {
        RoleRequestDto requestDto = roleConverter.toRoleRequestDto(dto);
        return ResponseHandler.success(roleService.update(id, requestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(roleService.delete(id));
    }
}
