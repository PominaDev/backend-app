package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.dto.request.WarrantyRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WarrantyService extends BaseService<WarrantyRequestDto, WarrantyResponseDto, Integer> {

    PageResponse<WarrantyInfoHistory> getWarrantyInfoHistory(PageRequest pageRequest, boolean forAdmin);

    /**
     * Lấy thông tin bảo hành theo mã cuộn tồn
     * @param maCuonTon
     * @return WarrantyInfoHistory
     */
    WarrantyInfoHistory getWarrantyInfoHistoryByMaCuonTon(String maCuonTon);

    PageResponse<WarrantyInfoHistory> filterWarrantyInfoHistory(
            PageRequest pageRequest,
            boolean forAdmin,
            List<String> filter,
            Boolean isValid,
            String status,
            String dateFrom,
            String dateTo,
            String sort
    );

    List<WarrantyInfoHistory> findAllWarrantyDetailWithFilter(List<String> filter, Boolean isValid, String status, String dateFrom, String dateTo, boolean forAdmin);
}
