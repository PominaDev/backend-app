package com.pomina.commonservices.excel.service;

import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.excel.entity.SysUserExport;
import com.pomina.commonservices.user_management.entity.SysRole;
import com.pomina.commonservices.user_management.service.impl.RoleServiceImpl;
import com.pomina.webapp.user_managerment.entity.SysUser;
import com.pomina.webapp.user_managerment.service.impl.SysUserManagermentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserExportService {

    private final ExcelService excelService;

    private final SysUserManagermentServiceImpl sysUserService;

    private final SysUserManagermentServiceImpl sysUserManagermentService;

    private final RoleServiceImpl roleService;

    public void exportUsers(HttpServletResponse response, List<String> roleNames, List<String> filter) {

        List<Integer> roleIds = roleService.findByRoleNames(roleNames)
                .stream()
                .map(SysRole::getId)
                .toList();

        List<SysUser> sysUserList = sysUserManagermentService.findAllFilter(filter, roleIds);
        
        // Gán STT
        AtomicInteger counter = new AtomicInteger(1);
        List<SysUserExport> sysUserExports = sysUserList.stream()
                .map(u -> {
                    SysUserExport export = new SysUserExport();
                    BeanUtils.copyProperties(u, export); // copy tất cả field từ SysUser sang SysUserExport
                    export.setStt(String.valueOf(counter.getAndIncrement())); // set thêm STT
                    return export;
                })
                .collect(Collectors.toList());

        // lấy tên người thao tác
        String userExport = sysUserService.findById(AuditUtil.getDefaultUser()).getHoVaTen();

        // biến đổi giờ thành chuẩn format
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = LocalDateTime.now(vietnamZone).format(formatter);

        // Biến fill vào template
        Map<String, Object> vars = Map.of(
                "date", formattedDate,
                "userExport", userExport
        );

        // Xuất file Excel dựa trên template có
        excelService.exportExcelWithTemplate(
                response,
                sysUserExports,
                "excel/information_user.xlsx",
                "Thong_tin_nguoi_dung",
                vars
        );
    }
}
