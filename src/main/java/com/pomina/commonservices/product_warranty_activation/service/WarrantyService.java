package com.pomina.commonservices.product_warranty_activation.service;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.service.BaseService;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.dto.request.WarrantyRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
            String sort
    );

    List<WarrantyInfoHistory> findAllWarrantyDetailWithFilter(List<String> filter, Boolean isValid, String status, boolean forAdmin);
}
