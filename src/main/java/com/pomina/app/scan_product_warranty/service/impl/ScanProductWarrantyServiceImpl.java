package com.pomina.app.scan_product_warranty.service.impl;

import com.pomina.app.scan_product_warranty.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.scan_product_warranty.dto.response.ProductDetailResponseDto;
import com.pomina.app.scan_product_warranty.dto.response.ScanProductWarrantyResponseDto;
import com.pomina.app.scan_product_warranty.service.ScanProductWarrantyService;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.service.LocationService;
import com.pomina.commonservices.product_warranty_activation.entity.Product;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import com.pomina.commonservices.product_warranty_activation.mapper.ProductMapper;
import com.pomina.commonservices.product_warranty_activation.mapper.WarrantyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.pomina.security.config.JwtAuthentication.getCurrentUserId;

@Service
@RequiredArgsConstructor
public class ScanProductWarrantyServiceImpl implements ScanProductWarrantyService {

    private final ProductMapper productMapper;

    private final WarrantyMapper warrantyMapper;

    private final LocationService locationService;

    @Override
    @Transactional
    public ScanProductWarrantyResponseDto scanQr(ScanProductWarrantyRequestDto scanProductWarrantyRequestDto) {

        String maCuonTon = scanProductWarrantyRequestDto.getMaCuonTon();
        Double latitude = scanProductWarrantyRequestDto.getLatitude();
        Double longitude = scanProductWarrantyRequestDto.getLongitude();

        Integer userIdCurr = getCurrentUserId();
        if (userIdCurr == null) throw new AppException(ErrorCode.UNAUTHORIZED);

        Product productAndWarrantyInfo = productMapper.findProductAndWarrantyByMaCuonTon(maCuonTon);

        if (productAndWarrantyInfo == null) throw new AppException(ErrorCode.INTERNAL_ERROR);

        // quét lần đầu
        if (productAndWarrantyInfo.getWarranty() == null) {
            // lấy location từ người dùng
            LocationResponseDto locationResponseDto = locationService.getLocationFromCoordinates(latitude, longitude);
            Integer bhPhaiMau = productAndWarrantyInfo.getBhPhaiMau();
            Integer bhAnMon = productAndWarrantyInfo.getBhAnMon();
            // insert warranty
            Warranty warranty = Warranty.builder()
                    .userId(userIdCurr)
                    .latitude(latitude)
                    .longitude(longitude)
                    .address01(locationResponseDto.getRoad())
                    .address02(locationResponseDto.getWard())
                    .address03(locationResponseDto.getDistrict())
                    .address04(locationResponseDto.getCity())
                    .address05(locationResponseDto.getCountry())
                    .countryCode(locationResponseDto.getCountryCode())
                    .maCuonTon(maCuonTon)
                    .fromWarrantyPhaiMau(LocalDateTime.now())
                    .toWarrantyPhaiMau(LocalDateTime.now().plusYears(bhPhaiMau))
                    .fromWarrantyAnMon(LocalDateTime.now())
                    .toWarrantyAnMon(LocalDateTime.now().plusYears(bhAnMon))
                    .build();

            // Fix tạm lỗi audit
            warranty.setIsDeleted(false);

            int resultInserted = warrantyMapper.insert(warranty);
            return ScanProductWarrantyResponseDto.builder()
                    .resultInserted(resultInserted)
                    .build();
        } else {
            // quét lần 2
            Integer userId = productAndWarrantyInfo.getWarranty().getUserId();
            if (!userIdCurr.equals(userId)) throw new AppException(ErrorCode.UNAUTHORIZED);
            ProductDetailResponseDto productDetailResponseDto = ProductDetailResponseDto.builder()
                    .productId(productAndWarrantyInfo.getProductId())
                    .maCuonTon(productAndWarrantyInfo.getMaCuonTon())
                    .tenSanPham(productAndWarrantyInfo.getTenSanPham())
                    .totalWeight(productAndWarrantyInfo.getTotalWeight())
                    .totalLength(productAndWarrantyInfo.getTotalLength())
                    .loaiCuon(productAndWarrantyInfo.getLoaiCuon())
                    .bhPhaiMau(productAndWarrantyInfo.getBhPhaiMau())
                    .bhAnMon(productAndWarrantyInfo.getBhAnMon())
                    .mALength(productAndWarrantyInfo.getMALength())
                    .mBLength(productAndWarrantyInfo.getMBLength())
                    .mCLength(productAndWarrantyInfo.getMCLength())
                    .status(productAndWarrantyInfo.getStatus())
                    .updatedAt(productAndWarrantyInfo.getUpdatedAt())
                    .build();

            return ScanProductWarrantyResponseDto.builder()
                    .productDetailResponseDto(productDetailResponseDto)
                    .build();
        }
    }
}
