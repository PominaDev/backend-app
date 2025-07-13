package com.pomina.erpapp.systemconfigsecurity.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginRequest;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginResponse;
import com.pomina.erpapp.systemconfigsecurity.sysservice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiAuth.BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiConstants.ApiAuth.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest loginRequest) {
        return ResponseHandler.success(authService.attemptLogin(loginRequest.getUsername(), loginRequest.getPassword()));
    }
}
