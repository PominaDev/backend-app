package com.pomina.webapp.pricing_policy_management.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.pricing_policy_management.converter.ChinhSachGiaBanChildConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanChildResponseDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachGiaBanChild;
import com.pomina.webapp.pricing_policy_management.mapper.ChinhSachGiaBanChildMapper;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachGiaBanChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChinhSachGiaBanChildServiceImpl implements ChinhSachGiaBanChildService {
    // Dependency injection
    private final ChinhSachGiaBanChildMapper chinhSachGiaBanChildMapper;
    private final ChinhSachGiaBanChildConverter chinhSachGiaBanChildConverter;

    @Override
    public int create(ChinhSachGiaBanChildRequestDto dto) {
        return chinhSachGiaBanChildMapper.insert(chinhSachGiaBanChildConverter.toEntity(dto));
    }

    @Override
    public int update(Integer id, ChinhSachGiaBanChildRequestDto dto) {
        ChinhSachGiaBanChild chinhSachGiaBanChildUpdate = chinhSachGiaBanChildConverter.toEntity(dto);
        chinhSachGiaBanChildUpdate.setUChinhSachGiaBanChildId(id);
        return chinhSachGiaBanChildMapper.update(chinhSachGiaBanChildUpdate);
    }

    @Override
    public ChinhSachGiaBanChildResponseDto getById(Integer id) {
        return chinhSachGiaBanChildConverter.toResponse(chinhSachGiaBanChildMapper.findById(id));
    }

    @Override
    public PageResponse<ChinhSachGiaBanChildResponseDto> search(PageRequest pageRequest) {
        List<ChinhSachGiaBanChild> chinhSachGiaBanChildList = chinhSachGiaBanChildMapper.findAllPaged(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);
        if (chinhSachGiaBanChildList == null || chinhSachGiaBanChildList.isEmpty()) {
            return PageResponse.empty(
                    pageRequest.getPage(),
                    pageRequest.getSize());
        }
        List<ChinhSachGiaBanChildResponseDto> chinhSachGiaBanChildResponse = chinhSachGiaBanChildConverter.toResponseList(chinhSachGiaBanChildList);

        int totalElements = chinhSachGiaBanChildMapper.countAll();
        return PageResponse.createPaged(
                chinhSachGiaBanChildResponse,
                pageRequest.getPage(),
                pageRequest.getSize(),
                totalElements);
    }

    @Override
    public int delete(Integer id) {
        return chinhSachGiaBanChildMapper.softDeleteById(id);
    }
}
