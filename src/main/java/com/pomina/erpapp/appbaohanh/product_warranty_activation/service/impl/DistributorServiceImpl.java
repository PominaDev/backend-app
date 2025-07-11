package com.pomina.erpapp.appbaohanh.product_warranty_activation.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.DistributorConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.DistributorRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.DistributorResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.mapper.DistributorMapper;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.DistributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {

    private final DistributorMapper distributorMapper;

    private final DistributorConverter distributorConverter;

    @Override
    public int create(DistributorRequestDto dto) {
        return 0;
    }

    @Override
    public int update(Integer id, DistributorRequestDto dto) {
        return 0;
    }

    @Override
    public DistributorResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public PageResponse<DistributorResponseDto> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
