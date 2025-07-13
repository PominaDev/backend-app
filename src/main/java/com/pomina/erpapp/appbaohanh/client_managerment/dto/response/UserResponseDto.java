package com.pomina.erpapp.appbaohanh.client_managerment.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String phoneNumber;
    private String fullName;
    private String taxCode;

    private String fullAddress;
    private String city;
    private String locationCode;

    private String bankName;
    private String bankNumber;
    private String description;

    // JOIN từ sys_role
    private String roleName;
    private Boolean isActive;
}
