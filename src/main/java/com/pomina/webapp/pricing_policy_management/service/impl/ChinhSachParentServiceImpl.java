package com.pomina.webapp.pricing_policy_management.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.pricing_policy_management.converter.ChinhSachParentConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachParentRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachParentResponseDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachParent;
import com.pomina.webapp.pricing_policy_management.mapper.ChinhSachParentMapper;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChinhSachParentServiceImpl implements ChinhSachParentService {

    // Dependency injection
    private final ChinhSachParentMapper chinhSachParentMapper;
    private final ChinhSachParentConverter chinhSachParentConverter;

    @Override
    public int create(ChinhSachParentRequestDto dto) {
        return chinhSachParentMapper.insert(chinhSachParentConverter.toEntity(dto));
    }

    @Override
    public int update(Integer id, ChinhSachParentRequestDto dto) {
        ChinhSachParent chinhSachParentUpdate = chinhSachParentConverter.toEntity(dto);
        chinhSachParentUpdate.setUChinhSachParentId(id);
        return chinhSachParentMapper.update(chinhSachParentUpdate);
    }

    @Override
    public ChinhSachParentResponseDto getById(Integer id) {
        return chinhSachParentConverter.toResponse(chinhSachParentMapper.findById(id));
    }

    @Override
    public PageResponse<ChinhSachParentResponseDto> search(PageRequest pageRequest) {
        List<ChinhSachParent> chinhSachParentList = chinhSachParentMapper.findAllPaged(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);
        if (chinhSachParentList == null || chinhSachParentList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }
        List<ChinhSachParentResponseDto> chinhSachParentResponse = chinhSachParentConverter.toResponseList(chinhSachParentList);

        int totalElements = chinhSachParentMapper.countAll();

        return PageResponse.createPaged(chinhSachParentResponse,
                pageRequest.getPage(),
                pageRequest.getSize(),
                totalElements);
    }

    @Override
    public int delete(Integer id) {
        return chinhSachParentMapper.softDeleteById(id);
    }
}
