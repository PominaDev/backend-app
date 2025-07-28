package com.pomina.appbaohanh.security.controller;

import com.pomina.appbaohanh.common.constant.ApiConstants;
import com.pomina.appbaohanh.common.handler.ApiResponse;
import com.pomina.appbaohanh.common.handler.ResponseHandler;
import com.pomina.appbaohanh.security.service.SysUserService;
import com.pomina.appbaohanh.security.sysmodel.LoginRequest;
import com.pomina.appbaohanh.security.sysmodel.LoginResponse;
import com.pomina.appbaohanh.security.sysmodel.LogoutRequest;
import com.pomina.appbaohanh.security.sysmodel.RefreshTokenRequest;
import com.pomina.appbaohanh.security.sysmodel.RegisterRequest;
import com.pomina.appbaohanh.security.sysmodel.RegisterResponse;
import com.pomina.appbaohanh.security.sysservice.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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

    private final SysUserService sysUserService;

    @PostMapping(ApiConstants.ApiAuth.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest loginRequest,
                                                            HttpServletRequest httpServletRequest) {
        loginRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        return ResponseHandler.success(authService.attemptLogin(loginRequest));
    }

    @PostMapping(ApiConstants.ApiAuth.LOGOUT)
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody @Validated LogoutRequest logoutRequest,
                                                    HttpServletRequest httpServletRequest) {
        logoutRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        return ResponseHandler.success(authService.attemptLogout(logoutRequest, httpServletRequest));
    }

    @PostMapping(ApiConstants.ApiAuth.REGISTER)
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody @Validated RegisterRequest registerRequest) {
        return ResponseHandler.success(sysUserService.registerUser(registerRequest));
    }

    @PostMapping(ApiConstants.ApiAuth.REFRESH_TOKEN)
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@RequestBody @Validated RefreshTokenRequest request,
                                                                   HttpServletRequest httpServletRequest) {
        request.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        return ResponseHandler.success(authService.refreshToken(request));
    }
}
