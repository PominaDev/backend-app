package com.pomina.erpapp.systemconfigsecurity.sysmodel;

import com.pomina.erpapp.appbaohanh.location_validation.dto.request.LocationRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+84|0)[1-9][0-9]{8,9}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String hoVaTen;

    @Size(max = 20, message = "Tax code must not exceed 20 characters")
    private String maSoThue;

    private Integer roleId = 6; // ROLE_USER

    private Boolean isActive = true; // TRUE

    // Register location
    private LocationRequestDto location;
}
