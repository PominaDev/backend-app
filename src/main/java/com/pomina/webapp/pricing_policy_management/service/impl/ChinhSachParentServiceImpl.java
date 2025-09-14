package com.pomina.webapp.pricing_policy_management.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.utils.AuditUtil;
import com.pomina.security.service.SysUserService;
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

    private final SysUserService sysUserService;

    @Override
    public int create(ChinhSachParentRequestDto dto) {

        // Kiểm tra nếu người dùng không truyền gì vào.
        if (dto == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Check ngày bắt đầu phải < ngày kết thúc
        if (dto.getDayBegin() != null && dto.getDayEnd() != null &&
        !dto.getDayBegin().isBefore(dto.getDayEnd())) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
        }

        ChinhSachParent chinhSachParent = chinhSachParentConverter.toEntity(dto);

        // set Audit trước khi insert
        AuditUtil.insert(chinhSachParent);

        // set user_upload
        chinhSachParent.setUserUpload(sysUserService.getCurUsername());

        return chinhSachParentMapper.insert(chinhSachParent);
    }

    @Override
    public int update(Integer id, ChinhSachParentRequestDto dto) {

        // kiem tra chinh sach can update co tồn tại không
        boolean exist = chinhSachParentMapper.existsById(id);
        if (!exist) {
            throw new AppException(ErrorCode.POLICY_NOT_FOUND);
        }

        // Check ngày bắt đầu phải < ngày kết thúc
        if (dto.getDayBegin() != null && dto.getDayEnd() != null &&
        !dto.getDayBegin().isBefore(dto.getDayEnd())) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
        }

        // Convert Dto to Entity && update
        ChinhSachParent chinhSachParentUpdate = chinhSachParentConverter.toEntity(dto);
        chinhSachParentUpdate.setUChinhSachParentId(id);

        // set Audit truoc khi update
        AuditUtil.update(chinhSachParentUpdate);

        return chinhSachParentMapper.update(chinhSachParentUpdate);
    }

    @Override
    public ChinhSachParentResponseDto getById(Integer id) {
        ChinhSachParent chinhSachParent = chinhSachParentMapper.findById(id);
        if (chinhSachParent == null) {
            throw new AppException(ErrorCode.POLICY_NOT_FOUND);
        }
        return chinhSachParentConverter.toResponse(chinhSachParent);
    }

    @Override
    public PageResponse<ChinhSachParentResponseDto> search(PageRequest pageRequest) {

        // lay data theo phân trang & filter
        List<ChinhSachParent> chinhSachParentList = chinhSachParentMapper.findAllPaged(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);
        if (chinhSachParentList == null || chinhSachParentList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        // convert from dto to entity
        List<ChinhSachParentResponseDto> chinhSachParentResponse = chinhSachParentConverter.toResponseList(chinhSachParentList);

        // dem tong theo filter
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
