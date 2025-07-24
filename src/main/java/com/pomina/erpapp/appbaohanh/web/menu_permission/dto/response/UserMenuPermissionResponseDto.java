package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuPermissionResponseDto {

  private String name;
  private List<MenuPermissionResponseDto> menu;
}