package com.pomina.webapp.pricing_policy_management.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.utils.AuditBatchUtil;
import com.pomina.common.utils.AuditUtil;
import com.pomina.common.utils.FileUtil;
import com.pomina.security.config.JwtAuthentication;
import com.pomina.webapp.pricing_policy_management.converter.ChinhSachGiaBanChildConverter;
import com.pomina.webapp.pricing_policy_management.converter.ChinhSachParentConverter;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelBody;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelHeader;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanTemplateDto;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachGiaBanChild;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachParent;
import com.pomina.webapp.pricing_policy_management.excel.ChinhSachTemplateReader;
import com.pomina.webapp.pricing_policy_management.mapper.ChinhSachGiaBanChildMapper;
import com.pomina.webapp.pricing_policy_management.mapper.ChinhSachParentMapper;
import com.pomina.webapp.pricing_policy_management.service.ChinhSachExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChinhSachExcelServiceImpl implements ChinhSachExcelService {

    private final ChinhSachParentMapper chinhSachParentMapper;

    private final ChinhSachGiaBanChildMapper chinhSachGiaBanChildMapper;

    private final ChinhSachParentConverter chinhSachParentConverter;

    private final ChinhSachGiaBanChildConverter chinhSachGiaBanChildConverter;

    private final ChinhSachTemplateReader chinhSachTemplateReader;

    @Transactional(rollbackFor = {Exception.class, AppException.class})
    @Override
    public int createList(ChinhSachExcelCreateDto chinhSachExcelCreateDto) {
        ChinhSachExcelHeader chinhSachExcelHeader = chinhSachExcelCreateDto.getHeader();
        if (chinhSachExcelHeader == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        List<ChinhSachExcelBody> chinhSachExcelBody = chinhSachExcelCreateDto.getBody();
        if (chinhSachExcelBody == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        int uChinhSachParentId = createChinhSachParent(chinhSachExcelHeader);
        createChinhSachGiaBanChild(chinhSachExcelBody, uChinhSachParentId);

        return 1;
    }

    @Override
    public ChinhSachGiaBanTemplateDto previewChinhSachFromFile(MultipartFile file) {
        FileUtil.validateExcelFile(file);
        return chinhSachTemplateReader.readTemplate(file, 500);
    }

    private int createChinhSachParent(ChinhSachExcelHeader chinhSachExcelHeader) throws AppException {
        ChinhSachParent chinhSachParent = chinhSachParentConverter.toChinhSachParent(chinhSachExcelHeader);
        if (chinhSachParent == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        /*
        Anhdev Quốc Đạt
        hiện tại ở BE em tạo dùm a vài cái emit
        chinh_sach_parent_type, truyền từ BE xuống là GIABAN
        chinh_sach_parent_code, truyền từ BE xuống là GIABAN
        chinh_sach_parent_name, truyền từ BE xuống là Chính Sách Chiết Khấu Giá Bán
        auto AP_CAYDUYETCHINHSACH vào bảng chinhSach Parent
        */
        chinhSachParent = ChinhSachParent.builder()
                .uChinhSachParentType("GIABAN")
                .uChinhSachParentCode("GIABAN")
                .uChinhSachParentName("Chính Sách Chiết Khấu Giá Bán")
                .userId(JwtAuthentication.getCurrentUserId())
                .masterGroupUserCode("AP_CAYDUYETCHINHSACH")
                .uStatus(chinhSachParent.getStatus())
                .dateUpload(chinhSachParent.getCreatedAt())
                .uDescription(chinhSachParent.getUDescription())
                .uDayBegin(chinhSachParent.getUDayBegin())
                .uDayEnd(chinhSachParent.getUDayEnd())
                .deptUpload(chinhSachParent.getDeptUpload())
                .userUpload(chinhSachParent.getUserUpload())
                .build();

        AuditUtil.insert(chinhSachParent);

        int resultInsertChinhSachParent = chinhSachParentMapper.insert(chinhSachParent);

        Integer uChinhSachParentId = chinhSachParent.getUChinhSachParentId();
        if (resultInsertChinhSachParent < 1 || uChinhSachParentId == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        return uChinhSachParentId;
    }

    private void createChinhSachGiaBanChild(List<ChinhSachExcelBody> chinhSachExcelBody, Integer uChinhSachParentId) throws AppException {
        List<ChinhSachGiaBanChild> listChinhSachGiaBanChild = chinhSachGiaBanChildConverter.toListChinhSachGiaBanChild(chinhSachExcelBody);
        if (listChinhSachGiaBanChild == null || listChinhSachGiaBanChild.isEmpty()) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        AuditBatchUtil.insertAll(listChinhSachGiaBanChild);

        listChinhSachGiaBanChild.forEach(e -> e.setUChinhSachParentId(uChinhSachParentId));

        int resultInsertChinhSachGiaBanChild = chinhSachGiaBanChildMapper.batchInsert(listChinhSachGiaBanChild);
        if (resultInsertChinhSachGiaBanChild < 1) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
    }
}
