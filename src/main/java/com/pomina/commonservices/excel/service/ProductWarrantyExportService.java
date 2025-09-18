package com.pomina.commonservices.excel.service;

import com.pomina.common.utils.AuditUtil;
import com.pomina.common.utils.DateTimeUtil;
import com.pomina.commonservices.excel.entity.WarrantyInfoHistoryExport;
import com.pomina.commonservices.product_warranty_activation.service.impl.WarrantyServiceImpl;
import com.pomina.webapp.user_managerment.service.impl.SysUserManagermentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWarrantyExportService {

    private final WarrantyServiceImpl warrantyService;
    private final ExcelService excelService;
    private final SysUserManagermentServiceImpl sysUserService;

    public void exportWarranty(HttpServletResponse response, Boolean isValid, List<String> filter, String status, String dateFrom, String dateTo, boolean forAdmin){
        List<WarrantyInfoHistoryExport> warrantyInfoHistoryList = warrantyService.findAllWarrantyDetailWithFilter(filter, isValid, status, dateFrom, dateTo, forAdmin);

        // Gán STT
        AtomicInteger counter = new AtomicInteger(1);
        List<WarrantyInfoHistoryExport>  warrantyInfoHistoryExportList = warrantyInfoHistoryList.stream()
                .map(u -> {
                    WarrantyInfoHistoryExport export = new WarrantyInfoHistoryExport();
                    BeanUtils.copyProperties(u, export); // copy tất cả field từ SysUser sang SysUserExport
                    export.setStt(String.valueOf(counter.getAndIncrement())); // set thêm STT
                    export.setLocationAccountFull(this.handlerLocationWarranty(export.getLocationAccount01(), export.getLocationAccount02(), export.getLocationAccount03(), export.getLocationAccount04())); // xử lý địa chỉ full
                    export.setLocationWarrantyFull(this.handlerLocationWarranty(export.getLocationWarranty01(), export.getLocationWarranty02(), export.getLocationWarranty03(), export.getLocationWarranty04())); // xử lý địa chỉ full
                    export.setStatus(export.getIsValid() ? "Hợp lệ" : "Không hợp lệ");
                    export.setHierarchyLevel(handlerRoleName(sysUserService.findById(u.getUserId()).getRoleName())); // xử lý bỏ ROLE_
                    export.setYearWarrantyAnMon(handlerYearWarranty(u.getFromWarrantyAnMon(), u.getToWarrantyAnMon()));
                    export.setYearWarrantyPhaiMau(handlerYearWarranty(u.getFromWarrantyAnMon(), u.getToWarrantyPhaiMau()));
                    export.setFromWarrantyAnMon(DateTimeUtil.convertToDateOnly(u.getFromWarrantyAnMon())); // bỏ giờ đi
                    export.setToWarrantyAnMon(DateTimeUtil.convertToDateOnly(u.getToWarrantyAnMon()));// bỏ giờ đi
                    export.setToWarrantyPhaiMau(DateTimeUtil.convertToDateOnly(u.getToWarrantyPhaiMau())); //bỏ giờ đi
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
//        excelService.exportExcelWithTemplate(
//                response,
//                warrantyInfoHistoryExportList,
//                "excel/information_warranty.xlsx",
//                "Thong_tin_bao_hanh",
//                vars
//        );
    }

    public String handlerLocationWarranty(String ... location){
        return Arrays.stream(location)
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining(", "));
    }

    public String handlerYearWarranty(String dateKichHoat, String dateWarranty){
        if (dateKichHoat == null || dateWarranty == null) {
            return "0";
        }

        // Nếu chỉ truyền yyyy-MM-dd, mình bổ sung "00:00:00" để parse đúng LocalDateTime
        if (dateKichHoat.length() == 10) {
            dateKichHoat += " 00:00:00";
        }
        if (dateWarranty.length() == 10) {
            dateWarranty += " 00:00:00";
        }

        LocalDateTime startDateTime = DateTimeUtil.parseToLocalDateTime(dateKichHoat);
        LocalDateTime endDateTime = DateTimeUtil.parseToLocalDateTime(dateWarranty);

        // Tính số năm giữa 2 mốc thời gian
        return ChronoUnit.YEARS.between(startDateTime, endDateTime) + "";
    }

    public String handlerRoleName(String roleName) {
        if (roleName == null || roleName.isEmpty()) {
            return roleName; // hoặc trả về "" tùy ý
        }
        String prefix = "ROLE_";
        if (roleName.startsWith(prefix)) {
            return roleName.substring(prefix.length()); // cắt bỏ prefix
        }
        return roleName;
    }

}
