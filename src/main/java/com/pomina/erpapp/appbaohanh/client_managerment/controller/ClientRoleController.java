package com.pomina.erpapp.appbaohanh.client_managerment.controller;

import com.pomina.erpapp.appbaohanh.client_managerment.converter.RoleConverter;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleCreateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleUpdateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.RoleResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.service.ClientRoleService;
import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
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
public class ClientRoleController extends BaseController<RoleCreateDto, RoleUpdateDto, RoleResponseDto, Integer> {

    // Dependency injection
    private final ClientRoleService clientRoleService;
    private final RoleConverter roleConverter;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> create(RoleCreateDto dto) {
        RoleRequestDto requestDto = roleConverter.toRoleRequestDto(dto);
        return ResponseHandler.success(clientRoleService.create(requestDto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(clientRoleService.getById(id));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<RoleResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(clientRoleService.search(pageRequest));
    }

    @Override
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody RoleUpdateDto dto) {
        RoleRequestDto requestDto = roleConverter.toRoleRequestDto(dto);
        return ResponseHandler.success(clientRoleService.update(id, requestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(clientRoleService.delete(id));
    }
}
