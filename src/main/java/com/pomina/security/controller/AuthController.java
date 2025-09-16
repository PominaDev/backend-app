package com.pomina.security.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.utils.EncryptionUtil;
import com.pomina.commonservices.user_activity.anotation.UserAction;
import com.pomina.security.service.SysUserService;
import com.pomina.security.sysmodel.LoginRequest;
import com.pomina.security.sysmodel.LoginResponse;
import com.pomina.security.sysmodel.LogoutRequest;
import com.pomina.security.sysmodel.RefreshTokenRequest;
import com.pomina.security.sysmodel.RegisterRequest;
import com.pomina.security.sysmodel.RegisterResponse;
import com.pomina.security.sysservice.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
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

    private final EncryptionUtil encryptionUtil;

    @PostMapping(ApiConstants.ApiAuth.LOGIN)
    @UserAction(actionName = "Đăng nhập")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest loginRequest,
                                                            HttpServletRequest httpServletRequest) throws AuthenticationException {
        loginRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
//        loginRequest.setPassword(encryptionUtil.decrypt(loginRequest.getPassword()));
        return ResponseHandler.success(authService.attemptLogin(loginRequest));
    }

    @PostMapping(ApiConstants.ApiAuth.LOGOUT)
    @UserAction(actionName = "Đăng xuất")
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
