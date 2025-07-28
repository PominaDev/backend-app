package com.pomina.appbaohanh.user_managerment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {
    private Integer id;
    private String roleName;
    private String description;
}
