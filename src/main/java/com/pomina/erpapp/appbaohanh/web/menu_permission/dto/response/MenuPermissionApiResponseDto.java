package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermissionApiResponseDto {

  private Integer status;
  private UserMenuPermissionResponseDto data;
}