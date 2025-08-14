package com.pomina.webapp.pricing_policy_management.service;

import com.pomina.webapp.pricing_policy_management.dto.excel.ChinhSachExcelCreateDto;

public interface ChinhSachExcelService {
    int createList(ChinhSachExcelCreateDto chinhSachExcelCreateDto);
}
