package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuStructured extends MenuPermissionResponseDto{
    List<MenuPermissionResponseDto> itemMenu;
}
