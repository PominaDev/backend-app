package com.pomina.webapp.product_warranty_activation_tracking.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import com.pomina.commonservices.product_warranty_activation.mapper.WarrantyMapper;
import com.pomina.webapp.product_warranty_activation_tracking.dto.request.ActivationWarrantyRequestDto;
import com.pomina.webapp.product_warranty_activation_tracking.service.ProductWarrantyActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductWarrantyActivationServiceImpl implements ProductWarrantyActivationService {

    private final WarrantyMapper warrantyMapper;

    /**
     * Description: Kích hoạt bảo hành sản phẩm thủ công.
     *
     * @param warrantyId id của thông tin bảo hành sản phẩm đã tạo
     *                   khi người dùng kích hoạt nhưng vị trí không hợp lệ
     * @param activationWarrantyRequestDto {@link ActivationWarrantyRequestDto}
     * </br>
     * Bao gồm isValid : trạng thái kích hoạt bảo hành và noted
     *
     * @return Integer kết quả số dòng dữ liệu thay đổi
     * @throws AppException Nếu không tìm thấy thông tin bảo hành sản phẩm
     */
    @Override
    @Transactional
    public Integer activateWarranty(Integer warrantyId, ActivationWarrantyRequestDto activationWarrantyRequestDto) {

        Warranty warrantyInfo = warrantyMapper.findById(warrantyId);
        if (Objects.isNull(warrantyInfo)) throw new AppException(ErrorCode.WARRANTY_NOT_FOUND);

        warrantyInfo.setIsValid(activationWarrantyRequestDto.getIsValid());
        warrantyInfo.setNote(activationWarrantyRequestDto.getNoted());

        return warrantyMapper.update(warrantyInfo);
    }
}
