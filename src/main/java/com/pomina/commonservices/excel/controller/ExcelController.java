package com.pomina.commonservices.excel.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.commonservices.excel.service.SysUserExportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiExcel.BASE)
@RequiredArgsConstructor
public class ExcelController {

    private final SysUserExportService sysUserExportService;

    @GetMapping(ApiConstants.ApiExcel.EXPORT_INFORMATION_USER)
    public void exportInformationUsers(
            HttpServletResponse response,
            @RequestParam(required = false) List<String> roleNames,
            @RequestParam(required = false) List<String> filter) {
        sysUserExportService.exportUsers(response, roleNames, filter);
    }
}
