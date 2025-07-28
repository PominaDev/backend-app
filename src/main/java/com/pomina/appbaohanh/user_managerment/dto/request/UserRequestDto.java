package com.pomina.appbaohanh.user_managerment.dto.request;

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
    private String phoneNumberNormalize;
    private String password;

    private String fullName;
    private String taxCode;

    private String bankName;
    private String bankNumber;
    private String description;

    private Integer roleId;
}
