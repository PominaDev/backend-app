package com.pomina.erpapp.appbaohanh.client_managerment.controller;

import com.pomina.erpapp.appbaohanh.client_managerment.converter.UserConverter;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserCreateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserUpdateDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.UserResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.service.ClientUserService;
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
@RequestMapping(ApiConstants.ApiClientManager.BASE)
@RequiredArgsConstructor
public class ClientUserController extends BaseController <UserCreateDto, UserUpdateDto, UserResponseDto, Integer> {

    // Dependency injection
    private final ClientUserService clientUserService;
    private final UserConverter userConverter;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody UserCreateDto dto) {
        UserRequestDto requestDto = userConverter.toUserRequestDto(dto);
        return ResponseHandler.success(clientUserService.create(requestDto));
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getById(@PathVariable("userId") Integer userId) {
        return ResponseHandler.success(clientUserService.getById(userId));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<UserResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(clientUserService.search(pageRequest));
    }

    @Override
    @PostMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("userId") Integer userId, @Valid @RequestBody UserUpdateDto dto) {
        UserRequestDto requestDto = userConverter.toUserRequestDto(dto);
        return ResponseHandler.success(clientUserService.update(userId, requestDto));
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("userId") Integer userId) {
        return ResponseHandler.success(clientUserService.delete(userId));
    }
}

