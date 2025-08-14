package com.pomina.webapp.pricing_policy_management.service;

import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.service.BaseService;
import com.pomina.webapp.pricing_policy_management.dto.request.ChinhSachGiaBanChildRequestDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanChildResponseDto;

public interface ChinhSachGiaBanChildService extends BaseService<ChinhSachGiaBanChildRequestDto, ChinhSachGiaBanChildResponseDto, Integer > {
    PageResponse<ChinhSachGiaBanChildResponseDto> getByUChinhSachParentId(PageRequest pageRequest, int uChinhSachParentId);
}
