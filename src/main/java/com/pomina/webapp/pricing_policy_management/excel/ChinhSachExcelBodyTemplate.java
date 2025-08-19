package com.pomina.webapp.pricing_policy_management.excel;

import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChinhSachExcelBodyTemplate {

    private List<ChinhSachExcelBody> chinhSachBody;

    private int lastRowIndex;
}
