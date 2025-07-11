package com.pomina.erpapp.systemconfigsecurity.controller;

import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginRequest;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginResponse;
import com.pomina.erpapp.systemconfigsecurity.sysservice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.attemptLogin(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
