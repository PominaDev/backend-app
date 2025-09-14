package com.pomina.commonservices.product_warranty_activation.service.impl;

import com.pomina.common.config.datasources.CustomDataSource;
import com.pomina.common.config.datasources.DataSourceType;
import com.pomina.common.enums.ScanProductWarrantyMessage;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.dto.request.WarrantyRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;
import com.pomina.commonservices.product_warranty_activation.mapper.WarrantyMapper;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pomina.security.config.JwtAuthentication.getCurrentUserId;

@Service
@RequiredArgsConstructor
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyMapper warrantyMapper;

    @Override
    public int create(WarrantyRequestDto dto) {
        return 0;
    }

    @Override
    public int update(Integer id, WarrantyRequestDto dto) {
        return 0;
    }

    @Override
    public WarrantyResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public PageResponse<WarrantyResponseDto> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    /**
     * Lấy lịch sử kích hoạt bảo hành
     * @return List<WarrantyInfoHistory>
     */
    @Override
    public PageResponse<WarrantyInfoHistory> getWarrantyInfoHistory(PageRequest pageRequest, boolean forAdmin) {

        // Tạm thời về sau sẽ nâng cấp tracking device
        // Nếu truyền cờ forAdmin (web) thì lấy tất cả lịch sử kích hoạt
        Integer userId = forAdmin ? null : getCurrentUserId();

        // Lấy danh sách lịch sử kích hoạt bảo hành
        List<WarrantyInfoHistory> warrantyInfoHistories = warrantyMapper.findWarrantyDetail(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest,
                userId);

        if (warrantyInfoHistories == null || warrantyInfoHistories.isEmpty()) {
            return null;
        }

        warrantyInfoHistories.stream()
                .filter(warranty -> !warranty.getIsValid()) // Lọc các bản ghi isValid == false
                .forEach(warranty -> warranty.setMessage(ScanProductWarrantyMessage.INVALID.getMessage())); // Đặt message cho từng bản ghi

        // Tính tổng số lượng bản ghi
        int totalElements = warrantyMapper.countWarrantyDetail(userId);

        return PageResponse.createPaged(warrantyInfoHistories, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @Override
    public WarrantyInfoHistory getWarrantyInfoHistoryByMaCuonTon(String maCuonTon) {
        return null;
    }


    @Override
    public PageResponse<WarrantyInfoHistory> filterWarrantyInfoHistory(PageRequest pageRequest, boolean forAdmin, List<String> filter, Boolean isValid, String status, String sort) {
        Integer userId = forAdmin ? null : getCurrentUserId();
        String orderByClause = buildSortClause(sort);
        // Lấy danh sách lịch sử kích hoạt bảo hành với filter
        List<WarrantyInfoHistory> warrantyInfoHistories = warrantyMapper.filterWarrantyDetail(
                filter,
                isValid,
                status,
                orderByClause,
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest,
                userId);

        if (warrantyInfoHistories == null || warrantyInfoHistories.isEmpty()) {
            return null;
        }

        // Tính tổng số lượng bản ghi
        int totalElements = warrantyMapper.countWarrantyInfoHistory(userId, filter, isValid, status);

        return PageResponse.createPaged(warrantyInfoHistories, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    private static final Map<String, String> FIELD_COLUMN_MAP = Map.of(
            "hoVaTen", "ho_va_ten",
            "phoneNumber", "phone_number",
            "tenSanPham", "ten_san_pham",
            "maCuonTon", "ma_cuon_ton",
            "createdAt", "created_at",
            "isValid", "is_valid",
            "status", "status"
    );

    private String buildSortClause(String sort) {
        if (sort == null || sort.isBlank()) {
            return "created_at DESC";
        }
        String[] parts = sort.split(":");
        String field = parts[0];
        String direction = parts.length > 1 ? parts[1].toUpperCase() : "ASC";
        String column = FIELD_COLUMN_MAP.get(field);
        if (column == null) {
            throw new IllegalArgumentException("Invalid sort field: " + field);
        }
        if (!direction.equals("ASC") && !direction.equals("DESC")) {
            direction = "ASC";
        }
        return column + " " + direction;
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public List<WarrantyInfoHistory> findAllWarrantyDetailWithFilter(List<String> filter, Boolean isValid, String status, boolean forAdmin) {
        Integer userId = forAdmin ? null : getCurrentUserId();
        return warrantyMapper.findAllWarrantyDetailWithFilter(filter, isValid,status, userId);
    }
}
