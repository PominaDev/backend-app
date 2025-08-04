package com.pomina.app.product_warranty_activation_scanning.controller;

import com.pomina.app.product_warranty_activation_scanning.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.product_warranty_activation_scanning.dto.response.ScanProductWarrantyResponseDto;
import com.pomina.app.product_warranty_activation_scanning.service.ScanProductWarrantyService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.service.WarrantyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller xử lý các yêu cầu liên quan đến kích hoạt sản phẩm.
 *
 * @author namnm
 * @version 1.0
 * @since 2025-08-04
 */
@RestController
@RequestMapping(ApiConstants.ApiWarranty.BASE)
@RequiredArgsConstructor
public class ScanProductWarrantyController {

    private final ScanProductWarrantyService scanProductWarrantyService;

    private final WarrantyService warrantyService;

    /**
     * Description: Kích hoạt sản phẩm (cuộn tôn) bằng QR Code.
     * <p>
     * Endpoint: /api/v1/warranties/activate-by-qr
     *
     * @param requestDto Chuỗi mã QR của sản phẩm và vị trí khi quét
     * @return {@link ScanProductWarrantyResponseDto} -
     * Có thêm sản phẩm thành công không ? và thông tin kích hoạt sản phẩm
     */
    @PostMapping(ApiConstants.ApiWarranty.ACTIVATE_BY_QR)
    public ResponseEntity<ApiResponse<ScanProductWarrantyResponseDto>> activateByQrCode(@RequestBody ScanProductWarrantyRequestDto requestDto) {
        return ResponseHandler.success(scanProductWarrantyService.activateByQrCode(requestDto));
    }

    /**
     * Description: Lấy thông tin kích hoạt sản phẩm của người dùng hiện tại.
     * <p>
     * Endpoint: /api/v1/warranties/history
     *
     * @param pageRequest Đối tượng chứa thông tin phân trang và sắp xếp (tùy chọn)
     * @return {@link WarrantyInfoHistory} -
     * Thông tin kích hoạt sản phẩm của người dùng hiện tại
     */
    @GetMapping(ApiConstants.ApiWarranty.GET_WARRANTY_INFO_HISTORY)
    public ResponseEntity<ApiResponse<PageResponse<WarrantyInfoHistory>>> getWarrantyInfoHistory(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(warrantyService.getWarrantyInfoHistory(pageRequest, false));
    }
}
