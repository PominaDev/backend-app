package com.pomina.commonservices.product_warranty_activation.service.impl;

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

        // Tính tổng số lượng bản ghi
        int totalElements = warrantyMapper.countWarrantyDetail(userId);

        return PageResponse.createPaged(warrantyInfoHistories, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @Override
    public WarrantyInfoHistory getWarrantyInfoHistoryByMaCuonTon(String maCuonTon) {
        return null;
    }
}
