package com.pomina.erpapp.appbaohanh.client_managerment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long userId;

    private String username;
    private String phoneNumber;
    private String password;

    private String fullName;
    private String taxCode;

    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;

    private String city;
    private String locationCode;

    private String bankName;
    private String bankNumber;
    private String description;

    private Integer roleId;
    private Boolean isActive;
}
