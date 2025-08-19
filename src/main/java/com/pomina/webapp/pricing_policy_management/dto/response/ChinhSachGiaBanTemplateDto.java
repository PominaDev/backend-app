package com.pomina.webapp.pricing_policy_management.dto.response;

import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelBody;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelHeader;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ChinhSachGiaBanTemplateDto {

    private ChinhSachExcelHeader header;

    private List<ChinhSachExcelBody> body;
}
