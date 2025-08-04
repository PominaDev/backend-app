package com.pomina.commonservices.user_management.dto.custom_mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private Integer userId;
    private String username;
    private String fullName;
    private String phoneNumber;

    private String roleName;

    private AddressUserProfile address;

    private Double latitude;
    private Double longitude;

    private String masterLocationCode;
    private String masterLocationName;
}
