package com.pomina.webapp.pricing_policy_management.dto.request.excel;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChinhSachExcelCreateDto {

    @NotNull
    private ChinhSachExcelHeader chinhSachExcelHeader;

    @NotNull
    private List<ChinhSachExcelBody> chinhSachExcelBody;
}
