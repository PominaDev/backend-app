package com.pomina.webapp.grant_approval.service.impl;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.grant_approval.converter.MasterGroupUserConverter;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserRequestDto;
import com.pomina.webapp.grant_approval.dto.response.MasterGroupUserResponseDto;
import com.pomina.webapp.grant_approval.entity.MasterGroupUser;
import com.pomina.webapp.grant_approval.mapper.MasterGroupUserMapper;
import com.pomina.webapp.grant_approval.service.MasterGroupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterGroupUserServiceImpl implements MasterGroupUserService {

    // Dependency injection
    private final MasterGroupUserMapper masterGroupUserMapper;
    private final MasterGroupUserConverter masterGroupUserConverter;

    @Override
    public int create(MasterGroupUserRequestDto dto) {
        MasterGroupUser masterGroupUser = masterGroupUserConverter.toEntity(dto);
        return masterGroupUserMapper.insert(masterGroupUser);
    }

    @Override
    public int update(Integer id, MasterGroupUserRequestDto dto) {
        MasterGroupUser masterGroupUserInfo = masterGroupUserMapper.findById(id);
        MasterGroupUser masterGroupUserUpdate = masterGroupUserConverter.toEntity(dto);
        masterGroupUserUpdate.setMasterGroupUserId(masterGroupUserInfo.getMasterGroupUserId());
        return masterGroupUserMapper.update(masterGroupUserUpdate);
    }


    @Override
    public Integer updateListMasterGroupUser(List<MasterGroupUserRequestDto> dtoList) {
        int updatedRows = 0;

        return updatedRows;
    }

    @Override
    public Boolean isExistByUserIdAndGroupCode(String groupCode, Integer userId) {
        return null;
    }

    @Override
    public MasterGroupUserResponseDto getById(Integer id) {
        MasterGroupUser masterGroupUser = masterGroupUserMapper.findById(id);
        if (masterGroupUser != null) {
            return masterGroupUserConverter.toResponse(masterGroupUser);
        }
        return null;
    }

    @Override
    public PageResponse<MasterGroupUserResponseDto> search(PageRequest pageRequest) {
        List<MasterGroupUser> masterGroupUserList = masterGroupUserMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);
        if (masterGroupUserList == null || masterGroupUserList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }
        List<MasterGroupUserResponseDto> masterGroupUserResponse = masterGroupUserConverter.toResponseList(masterGroupUserList);
        int totalElements = masterGroupUserMapper.countAll();
        return PageResponse.createPaged(masterGroupUserResponse, pageRequest.getPage(), pageRequest.getSize(),
                totalElements);
    }

    @Override
    public int delete(Integer id) {
        return masterGroupUserMapper.softDeleteById(id);
    }

    @Override
    public List<MasterGroupUserResponseDto> getAllMasterGroupUser() {
        return List.of();
    }

    @Override
    public List<MasterGroupUserResponseDto> getAllMasterGroupUserByGroupCode(String groupCode) {
        return List.of();
    }

    @Override
    public Integer createListMasterGroupUser(List<MasterGroupUserRequestDto> dtoList) {
        return null;
    }
}