package com.pomina.erpapp.appbaohanh.client_managerment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto {
    @NotBlank(message = "Role ID is required")
    private Integer id;
    @NotBlank(message = "Role name is required")
    @Size(min = 4, max = 50, message = "Role name must be between 4 and 50 characters")
    private String roleName;
    @NotBlank(message = "Description is required")
    @Size(max = 255)
    private String description;
}
