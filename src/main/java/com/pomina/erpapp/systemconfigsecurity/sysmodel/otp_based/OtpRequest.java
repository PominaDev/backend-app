package com.pomina.erpapp.systemconfigsecurity.sysmodel.otp_based;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequest {

    @NotBlank
    private String phoneNumber;
}
