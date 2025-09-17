package com.pomina.security.sysmodel;

import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.security.sysmodel.otp_based.OtpResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    private String username;

    private String phoneNumber;

    private String hoVaTen;

    private String maSoThue;

    private String roleId;

    private Boolean isActive;

    private LocationResponseDto location;

    private OtpResponse otpResponse;
}
