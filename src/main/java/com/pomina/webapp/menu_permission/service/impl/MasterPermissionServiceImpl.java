package com.pomina.app.menu_permission.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.app.menu_permission.converter.MasterPermissionConverter;
import com.pomina.app.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.app.menu_permission.dto.response.MasterPermissionResponseDto;
import com.pomina.app.menu_permission.entity.MasterPermission;
import com.pomina.app.menu_permission.mapper.MasterPermissionMapper;
import com.pomina.app.menu_permission.service.MasterPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterPermissionServiceImpl implements MasterPermissionService {

    // Dependency injection
    private final MasterPermissionMapper masterPermissionMapper;
    private final MasterPermissionConverter masterPermissionConverter;

    @Override
    public int create(MasterPermissionRequestDto dto) {
        MasterPermission masterPermissionCreate = masterPermissionConverter.toEntity(dto);
        return masterPermissionMapper.insert(masterPermissionCreate);
    }

    @Override
    public int update(Integer id, MasterPermissionRequestDto dto) {
        MasterPermission masterPermissionInfo = masterPermissionMapper.findById(id);
        MasterPermission masterPermissionUpdate = masterPermissionConverter.toEntity(dto);
        masterPermissionUpdate.setMasterPermissionId(masterPermissionInfo.getMasterPermissionId());
        return masterPermissionMapper.update(masterPermissionUpdate);
    }

    @Override
    public MasterPermissionResponseDto getById(Integer id) {
        MasterPermission masterPermission = masterPermissionMapper.findById(id);
        if (masterPermission != null) {
            return masterPermissionConverter.toResponse(masterPermission);
        }
        return null;
    }

    @Override
    public PageResponse<MasterPermissionResponseDto> search(PageRequest pageRequest) {
        List<MasterPermission> masterPermissionList = masterPermissionMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (masterPermissionList == null || masterPermissionList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<MasterPermissionResponseDto> masterPermissionResponse = masterPermissionConverter.toResponseList(masterPermissionList);

        int totalElements = masterPermissionMapper.countAll();

        return PageResponse.createPaged(masterPermissionResponse, pageRequest.getPage(), pageRequest.getSize(),
                totalElements);
    }

    @Override
    public int delete(Integer id) {return masterPermissionMapper.softDeleteById(id); }
}
