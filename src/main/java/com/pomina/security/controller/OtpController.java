package com.pomina.security.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.security.sysmodel.LoginResponse;
import com.pomina.security.sysmodel.otp_based.OtpRequest;
import com.pomina.security.sysmodel.otp_based.OtpResponse;
import com.pomina.security.sysmodel.otp_based.VerifyOtpRequest;
import com.pomina.security.sysservice.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiAuth.BASE)
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping(path = ApiConstants.ApiAuth.OTP_SEND)
    public ResponseEntity<ApiResponse<OtpResponse>> sendOtp(@Valid @RequestBody OtpRequest otpRequest) {
        return ResponseHandler.success(otpService.sendOtp(otpRequest));
    }

    @PostMapping(path = ApiConstants.ApiAuth.OTP_VERIFY)
    public ResponseEntity<ApiResponse<LoginResponse>> verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseHandler.success(otpService.verifyOtp(verifyOtpRequest));
    }
}
