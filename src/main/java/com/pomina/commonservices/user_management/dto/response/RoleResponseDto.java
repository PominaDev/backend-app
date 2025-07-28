package com.pomina.commonservices.user_management.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private Integer id;
    private String roleName;
    private String description;
}
