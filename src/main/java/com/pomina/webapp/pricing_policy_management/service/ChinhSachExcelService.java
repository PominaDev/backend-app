package com.pomina.webapp.pricing_policy_management.service;

import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelCreateDto;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanTemplateDto;
import org.springframework.web.multipart.MultipartFile;

public interface ChinhSachExcelService {

    int createList(ChinhSachExcelCreateDto chinhSachExcelCreateDto);

    ChinhSachGiaBanTemplateDto previewChinhSachFromFile(MultipartFile file);
}
