package com.pomina.webapp.menu_permission.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.menu_permission.converter.MasterPermissionConverter;
import com.pomina.webapp.menu_permission.dto.request.MasterPermissionRequestDto;
import com.pomina.webapp.menu_permission.dto.response.MasterPermissionResponseDto;
import com.pomina.webapp.menu_permission.entity.MasterPermission;
import com.pomina.webapp.menu_permission.mapper.MasterPermissionMapper;
import com.pomina.webapp.menu_permission.service.MasterPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Integer createListPermisstion(List<MasterPermissionRequestDto> requestDtoList) {
        Integer createdRows = 0;
        List<MasterPermission> masterPermissionCreate = masterPermissionConverter.toEntityList(requestDtoList);
        for (MasterPermission entity : masterPermissionCreate) {
          createdRows += masterPermissionMapper.insert(entity);
        }
        return createdRows;
    }

    @Override
    public int update(Integer id, MasterPermissionRequestDto dto) {
        MasterPermission masterPermissionInfo = masterPermissionMapper.findById(id);
        MasterPermission masterPermissionUpdate = masterPermissionConverter.toEntity(dto);
        masterPermissionUpdate.setMasterPermissionId(masterPermissionInfo.getMasterPermissionId());
        return masterPermissionMapper.update(masterPermissionUpdate);
    }


    @Override
    public Integer updateListPermisstion(List<MasterPermissionRequestDto> requestDtoList) {
        Integer updatedRows = 0;
        List<MasterPermission> masterPermissionCreate = masterPermissionConverter.toEntityList(requestDtoList);
        for (MasterPermission entity : masterPermissionCreate) {
            Byte find = masterPermissionMapper.findByUserIdAndMenuId(entity);
            if (!Objects.isNull(find)) {
                updatedRows += masterPermissionMapper.update(entity);
            }else{
                updatedRows += masterPermissionMapper.insert(entity);
            }
        }
        return updatedRows;
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
