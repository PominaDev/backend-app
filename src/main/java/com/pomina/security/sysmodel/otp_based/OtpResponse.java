package com.pomina.security.sysmodel.otp_based;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OtpResponse {

    private Boolean status;

    private String otpToken;
}
