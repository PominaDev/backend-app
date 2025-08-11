package com.pomina.app.role.controller;

import com.pomina.app.role.dto.response.MobileRoleResponeseDto;
import com.pomina.app.role.service.Impl.MobileRoleServiceImpl;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiMobileRole.BASE)
@RequiredArgsConstructor
public class MobileRoleController {
    public final MobileRoleServiceImpl mobileRoleService;

    @GetMapping(ApiConstants.ApiMobileRole.GET_ALL_ROLE)
    public ResponseEntity<ApiResponse<List<MobileRoleResponeseDto>>> getListMobileRole(){
        return ResponseHandler.success(mobileRoleService.findAll());
    }
}
