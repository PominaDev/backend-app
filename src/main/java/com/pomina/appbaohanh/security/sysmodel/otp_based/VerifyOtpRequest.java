package com.pomina.appbaohanh.security.sysmodel.otp_based;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpRequest {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String otp;
}
