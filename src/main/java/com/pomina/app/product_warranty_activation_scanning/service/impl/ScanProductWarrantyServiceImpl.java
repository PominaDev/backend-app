package com.pomina.app.product_warranty_activation_scanning.service.impl;

import com.pomina.app.product_warranty_activation_scanning.dto.request.ScanProductWarrantyRequestDto;
import com.pomina.app.product_warranty_activation_scanning.dto.response.ScanProductWarrantyResponseDto;
import com.pomina.app.product_warranty_activation_scanning.service.ScanProductWarrantyService;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.CheckLocationResponse;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.service.LocationService;
import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.WarrantyInfoHistory;
import com.pomina.commonservices.product_warranty_activation.entity.Warranty;
import com.pomina.commonservices.product_warranty_activation.mapper.WarrantyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.pomina.security.config.JwtAuthentication.getCurrentUserId;

@Service
@RequiredArgsConstructor
public class ScanProductWarrantyServiceImpl implements ScanProductWarrantyService {

    private final WarrantyMapper warrantyMapper;

    private final LocationService locationService;

    @Override
    @Transactional(rollbackFor = {
            SQLException.class,
            PersistenceException.class,
            DataAccessException.class,
            AppException.class
    },
    isolation = Isolation.SERIALIZABLE)
    public ScanProductWarrantyResponseDto activateByQrCode(ScanProductWarrantyRequestDto scanProductWarrantyRequestDto) {

        String maCuonTon = scanProductWarrantyRequestDto.getMaCuonTon();
        Double latitude = scanProductWarrantyRequestDto.getLatitude();
        Double longitude = scanProductWarrantyRequestDto.getLongitude();

        Integer userIdCurr = getCurrentUserId();
        if (Objects.isNull(userIdCurr)) throw new AppException(ErrorCode.UNAUTHORIZED);

        WarrantyInfoHistory warrantyInfoHistory = warrantyMapper.findWarrantyDetailByMaCuonTon(maCuonTon);

        if (Objects.isNull(warrantyInfoHistory)) throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);

        // quét lần đầu
        if (Objects.isNull(warrantyInfoHistory.getFromWarrantyAnMon())) {

            // Check location có nằm trong khu vực đã đăng ký không
            Boolean isValidLocation = isCheckLocation(latitude, longitude);

            // lấy location từ người dùng
            LocationResponseDto locationResponseDto = locationService.getLocationFromCoordinates(latitude, longitude);

            // insert warranty
            int resultInserted = insertWarranty(scanProductWarrantyRequestDto, userIdCurr, warrantyInfoHistory, locationResponseDto, isValidLocation);
            if (resultInserted < 1) {
                return ScanProductWarrantyResponseDto.builder()
                        .resultInserted(resultInserted)
                        .productDetailResponseDto(null)
                        .build();
            }

            warrantyInfoHistory = warrantyMapper.findWarrantyDetailByMaCuonTon(maCuonTon);

            return ScanProductWarrantyResponseDto.builder()
                    .resultInserted(resultInserted)
                    .productDetailResponseDto(warrantyInfoHistory)
                    .build();
        } else {
            // quét lần 2
            Integer userId = warrantyInfoHistory.getUserId();
            if (!userIdCurr.equals(userId)) throw new AppException(ErrorCode.UNAUTHORIZED);

            return ScanProductWarrantyResponseDto.builder()
                    .productDetailResponseDto(warrantyInfoHistory)
                    .build();
        }
    }

    private int insertWarranty(ScanProductWarrantyRequestDto scanProductWarrantyRequestDto, Integer userIdCurr, WarrantyInfoHistory warrantyInfoHistory, LocationResponseDto locationResponseDto, Boolean isValidLocation) {
        LocalDateTime timeNow = LocalDateTime.now();

        // insert warranty
        Warranty warranty = Warranty.builder()
                .userId(userIdCurr)
                .latitude(scanProductWarrantyRequestDto.getLatitude())
                .longitude(scanProductWarrantyRequestDto.getLongitude())
                .address01(locationResponseDto.getRoad())
                .address02(locationResponseDto.getWard())
                .address03(locationResponseDto.getDistrict())
                .address04(locationResponseDto.getCity())
                .address05(locationResponseDto.getCountry())
                .countryCode(locationResponseDto.getCountryCode())
                .maCuonTon(scanProductWarrantyRequestDto.getMaCuonTon())
                .fromWarrantyPhaiMau(timeNow)
                .toWarrantyPhaiMau(LocalDateTime.now().plusYears(warrantyInfoHistory.getBhPhaiMau()))
                .fromWarrantyAnMon(timeNow)
                .toWarrantyAnMon(LocalDateTime.now().plusYears(warrantyInfoHistory.getBhAnMon()))
                .build();

        // Fix tạm lỗi audit
        warranty.setCreatedAt(timeNow);
        warranty.setUpdatedAt(timeNow);
        warranty.setIsDeleted(false);

        // Default nếu đúng phạm vi truy cập
        warranty.setIsValid(Boolean.TRUE.equals(isValidLocation));

        return warrantyMapper.insert(warranty);
    }

    private Boolean isCheckLocation(Double latitude, Double longitude) {
        // Check location
        CheckLocationResponse checkLocationResponse = locationService.checkLocation(
                LocationRequestDto.builder()
                        .latitude(latitude)
                        .longitude(longitude)
                        .build()
        );

        if (Boolean.FALSE.equals(checkLocationResponse.getIsWithinLocation())) {
            return false;
        }

        return true;
    }
}
