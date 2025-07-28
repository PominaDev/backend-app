package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuPermissionResponseDto extends MenuResponseDto {
    PermissionResponseDto permission;
}
