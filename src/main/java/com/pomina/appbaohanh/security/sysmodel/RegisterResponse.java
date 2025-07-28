package com.pomina.appbaohanh.security.sysmodel;

import com.pomina.appbaohanh.location.dto.response.LocationResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private String username;

    private String phoneNumber;

    private String hoVaTen;

    private String maSoThue;

    private String roleId;

    private Boolean isActive;

    private LocationResponseDto location;
}
