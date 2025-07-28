package com.pomina.appbaohanh.user_managerment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be 10 to 15 digits")
    private String phoneNumberNormalize;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Size(max = 50, message = "Tax code must not exceed 50 characters")
    private String taxCode;

    @Size(max = 255)
    private String address1;

    @Size(max = 255)
    private String address2;

    @Size(max = 255)
    private String address3;

    @Size(max = 255)
    private String address4;

    @Size(max = 255)
    private String address5;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String locationCode;

    @Size(max = 255)
    private String bankName;

    @Size(max = 50)
    private String bankNumber;

    @Size(max = 255)
    private String description;

    @NotNull(message = "Role is required")
    private Integer roleId;

    private Boolean isActive;
}
