package com.pomina.app.profile.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequestDto {

    private String fullName;
    private String phoneNumber;

    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;

    private Double latitude;
    private Double longitude;
}
