package com.pomina.webapp.menu_permission.controller;


import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.webapp.menu_permission.dto.response.MenuStructured;
import com.pomina.webapp.menu_permission.service.MasterMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiUserMenuPermission.BASE)
@RequiredArgsConstructor
public class UserMenuPermissionController {
    private final MasterMenuService masterMenuService;
    @GetMapping(ApiConstants.ApiUserMenuPermission.GET_BY_USER_ID)
    public ResponseEntity<ApiResponse<List<MenuStructured>>> getMenuPermissionByUserId(@PathVariable("userId") Integer userId) {
        return ResponseHandler.success(masterMenuService.getMenuPermissionByUserId(userId));
    }
}
