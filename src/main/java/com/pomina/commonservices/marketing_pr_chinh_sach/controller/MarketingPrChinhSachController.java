package com.pomina.commonservices.marketing_pr_chinh_sach.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.marketing_pr_chinh_sach.converter.MarketingPrChinhSachConverter;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachCreateDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachRequestDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.request.MarketingPrChinhSachUpdateDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.dto.response.MarketingPrChinhSachResponseDto;
import com.pomina.commonservices.marketing_pr_chinh_sach.service.MarketingPrChinhSachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiMarketingPrChinhSach.BASE)
@RequiredArgsConstructor
public class MarketingPrChinhSachController
        extends BaseController<MarketingPrChinhSachCreateDto, MarketingPrChinhSachUpdateDto, MarketingPrChinhSachResponseDto, Integer> {

    // Dependency injection
    private final MarketingPrChinhSachService marketingPrChinhSachService;
    private final MarketingPrChinhSachConverter marketingPrChinhSachConverter;

    @Override
    @PostMapping(ApiConstants.ApiMarketingPrChinhSach.CREATE)
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody MarketingPrChinhSachCreateDto dto) {

        // convert from createDto to requestDto
        MarketingPrChinhSachRequestDto requestDto = marketingPrChinhSachConverter.toMarketingPrChinhSachRequestDto(dto);
        return ResponseHandler.success(marketingPrChinhSachService.create(requestDto));
    }

    @Override
    @GetMapping(ApiConstants.ApiMarketingPrChinhSach.GET_BY_ID)
    public ResponseEntity<ApiResponse<MarketingPrChinhSachResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(marketingPrChinhSachService.getById(id));
    }

    @Override
    @GetMapping(ApiConstants.ApiMarketingPrChinhSach.GET_ALL)
    public ResponseEntity<ApiResponse<PageResponse<MarketingPrChinhSachResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(marketingPrChinhSachService.search(pageRequest));
    }

    @Override
    @PutMapping(ApiConstants.ApiMarketingPrChinhSach.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody MarketingPrChinhSachUpdateDto dto) {

        // convert from updateDto to requestDto
        MarketingPrChinhSachRequestDto requestDto = marketingPrChinhSachConverter.toMarketingPrChinhSachRequestDto(dto);
        return ResponseHandler.success(marketingPrChinhSachService.update(id, requestDto));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiMarketingPrChinhSach.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(marketingPrChinhSachService.delete(id));
    }
}
