package com.pomina.erpapp.systemconfigsecurity.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.LoginResponse;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.otp_based.OtpRequest;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.otp_based.OtpResponse;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.otp_based.VerifyOtpRequest;
import com.pomina.erpapp.systemconfigsecurity.sysservice.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiAuth.BASE)
@RequiredArgsConstructor
public class OtpController {

    private final AuthService authService;

    @PostMapping(path = ApiConstants.ApiAuth.OTP_SEND, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<OtpResponse>> sendOtp(@Valid @RequestBody OtpRequest otpRequest) {
        return ResponseHandler.success(authService.sendOtp(otpRequest));
    }

    @PostMapping(path = ApiConstants.ApiAuth.OTP_VERIFY, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoginResponse>> verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseHandler.success(authService.verifyOtp(verifyOtpRequest));
    }
}
