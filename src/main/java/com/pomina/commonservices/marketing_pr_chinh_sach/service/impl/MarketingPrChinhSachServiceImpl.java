package com.pomina.commonservices.marketing_pr_chinh_sach.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.marketing_pr_chinh_sach.converter.MarketingPrChinhSachConverter;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachRequestDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.response.MarketingPrChinhSachResponseDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.entity.MarketingPrChinhSach;
import com.pomina.commonservices.marketing_pr_chinh_sach.mapper.MarketingPrChinhSachMapper;
import com.pomina.commonservices.marketing_pr_chinh_sach.service.MarketingPrChinhSachService;
import com.pomina.security.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketingPrChinhSachServiceImpl implements MarketingPrChinhSachService {

    // Dependency injection
    private final MarketingPrChinhSachMapper marketingPrChinhSachMapper;

    private final MarketingPrChinhSachConverter marketingPrChinhSachConverter;

    private final SysUserService sysUserService;

    @Override
    public int create(MarketingPrChinhSachRequestDto dto) {

        // kiểm tra nếu người dùng không truyền gì vào.
        if (dto == null) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // kiểm tra ngày bắt đầu phải nhỏ hơn ngày kết thúc
        if (dto.getUDayBegin() != null && dto.getUDayEnd() != null &&
                !dto.getUDayBegin().isBefore(dto.getUDayEnd())) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
        }

        MarketingPrChinhSach marketingPrChinhSach = marketingPrChinhSachConverter.toEntity(dto);

        // set Audit trước khi insert
        AuditUtil.insert(marketingPrChinhSach);

        // set userUpload
        marketingPrChinhSach.setUserUpload(sysUserService.getCurUsername());

        return marketingPrChinhSachMapper.insert(marketingPrChinhSach);
    }


    @Override
    public int update(Integer id, MarketingPrChinhSachRequestDto dto) {

        // kiểm tra chính sách có tồn tại không
        if (!marketingPrChinhSachMapper.existsById(id)) {
            throw new AppException(ErrorCode.POLICY_NOT_FOUND);
        }

        // kiểm tra ngày bắt đầu phải nhỏ hơn ngày kết thúc
        if (dto.getUDayBegin() != null && dto.getUDayEnd() != null &&
                !dto.getUDayBegin().isBefore(dto.getUDayEnd())) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
        }

        // convert dto to entity && set id là id được truyền từ form
        MarketingPrChinhSach marketingPrChinhSachUpdate = marketingPrChinhSachConverter.toEntity(dto);
        marketingPrChinhSachUpdate.setUMarketingPrChinhSachId(id);

        // set audit
        AuditUtil.update(marketingPrChinhSachUpdate);

        return marketingPrChinhSachMapper.update(marketingPrChinhSachUpdate);
    }

    @Override
    public MarketingPrChinhSachResponseDto getById(Integer id) {

        MarketingPrChinhSach marketingPrChinhSach = marketingPrChinhSachMapper.findById(id);
        if (marketingPrChinhSach == null) {
            throw new AppException(ErrorCode.POLICY_NOT_FOUND);
        }
        return marketingPrChinhSachConverter.toResponse(marketingPrChinhSach);
    }

    @Override
    public PageResponse<MarketingPrChinhSachResponseDto> search(PageRequest pageRequest) {

        // lấy data theo phân trang và filter
        List<MarketingPrChinhSach> marketingPrChinhSachList = marketingPrChinhSachMapper.findAllPaged(
                pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);
        if (marketingPrChinhSachList == null || marketingPrChinhSachList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        // convert dto to entity
        List<MarketingPrChinhSachResponseDto> marketingPrChinhSachResponseDtoList = marketingPrChinhSachConverter.toResponseList(marketingPrChinhSachList);

        // dem tong theo filter
        int totalElements = marketingPrChinhSachMapper.countAll();

        return PageResponse.createPaged(marketingPrChinhSachResponseDtoList,
                pageRequest.getPage(),
                pageRequest.getSize(),
                totalElements);
    }

    @Override
    public int delete(Integer id) { return marketingPrChinhSachMapper.softDeleteById(id);}
}
