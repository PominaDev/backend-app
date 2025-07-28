package com.pomina.app.menu_permission.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuPermissionResponseDto extends MenuResponseDto {
    PermissionResponseDto permission;
}
