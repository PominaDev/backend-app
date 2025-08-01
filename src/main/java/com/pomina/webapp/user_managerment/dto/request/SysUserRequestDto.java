package com.pomina.webapp.user_managerment.dto.request;

import com.pomina.common.model.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserRequestDto extends BaseEntity {
    private Integer userId;

    @Size(max = 255, message = "Username must not exceed 255 characters")
    private String username;
    @Size(max = 255, message = "Password must not exceed 255 characters")
    private String password;
    private String hoVaTen;
    private String phoneNumber;
    private String maSoThue;
    //@NotBlank(message = "Master location code must not be empty")
    private String masterLocationCode;
    private String bankName;
    private String bankNumber;
    private String description;
    @NotNull(message = "Role ID must not be null")
    private Integer roleId;
    private Boolean isActive;
    private Integer oldId;

    // u_location
    private Double latitude;
    private Double longitude;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String fullAddress;

}
