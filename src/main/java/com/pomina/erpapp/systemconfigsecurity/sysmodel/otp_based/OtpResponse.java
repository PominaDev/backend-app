package com.pomina.erpapp.systemconfigsecurity.sysmodel.otp_based;

import com.vonage.client.verify.VerifyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OtpResponse {

    private Boolean status;
}
