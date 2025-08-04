package com.pomina.webapp.grant_approval.service;

import com.pomina.common.service.BaseService;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserRequestDto;
import com.pomina.webapp.grant_approval.dto.response.MasterGroupUserResponseDto;

import java.util.List;

public interface MasterGroupUserService extends BaseService<MasterGroupUserRequestDto, MasterGroupUserResponseDto, Integer> {
    List<MasterGroupUserResponseDto> getAllMasterGroupUser();
    List<MasterGroupUserResponseDto> getAllMasterGroupUserByGroupCode(String groupCode);

    Integer createListMasterGroupUser(List<MasterGroupUserRequestDto> dtoList);

    Integer updateListMasterGroupUser(List<MasterGroupUserRequestDto> dtoList);

    Boolean isExistByUserIdAndGroupCode(String groupCode, Integer userId);
}
