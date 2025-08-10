package com.pomina.webapp.policy_management.service;

import com.pomina.common.service.BaseService;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentRequestDto;
import com.pomina.webapp.policy_management.dto.response.UPolicyParentResponseDto;

import java.util.List;

public interface UPolicyParentService extends BaseService<UPolicyParentRequestDto, UPolicyParentResponseDto, Integer> {
    List<UPolicyParentResponseDto> getAllUPolicyParent();
    List<UPolicyParentResponseDto> getAllUPolicyParentByCode(String code);
    Integer softDeleteList(List<Integer> idList);
}
