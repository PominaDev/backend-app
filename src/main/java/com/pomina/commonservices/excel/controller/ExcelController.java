package com.pomina.commonservices.excel.controller;

import com.google.firebase.auth.UserInfo;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.entity.SysUserExport;
import com.pomina.commonservices.excel.service.ExcelService;
import com.pomina.commonservices.excel.service.SysUserExportService;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.webapp.user_managerment.controller.SysUserManagermentController;
import com.pomina.webapp.user_managerment.entity.SysUser;
import com.pomina.webapp.user_managerment.service.SysUserManagermentService;
import com.pomina.webapp.user_managerment.service.impl.SysUserManagermentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.ApiExcel.BASE)
@RequiredArgsConstructor
public class ExcelController {

    private final SysUserExportService sysUserExportService;

    @GetMapping(ApiConstants.ApiExcel.EXPORT_INFORMATION_USER)
    public void exportInformationUsers(HttpServletResponse response, @RequestParam List<Integer> ids) {
        sysUserExportService.exportUsers(response, ids);
    }
}
