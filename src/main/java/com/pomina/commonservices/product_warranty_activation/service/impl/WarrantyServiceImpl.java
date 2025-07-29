package com.pomina.commonservices.product_warranty_activation.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.product_warranty_activation.dto.request.WarrantyRequestDto;
import com.pomina.commonservices.product_warranty_activation.dto.response.WarrantyResponseDto;
import com.pomina.commonservices.product_warranty_activation.mapper.WarrantyMapper;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
