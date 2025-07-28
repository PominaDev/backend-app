package com.pomina.commonservices.user.management.dto.response;

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

    private String bankName;
    private String bankNumber;
    private String description;

    // JOIN sys_role
    private String roleName;

    // JOIN u_location
    private Integer locationId;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;
}
