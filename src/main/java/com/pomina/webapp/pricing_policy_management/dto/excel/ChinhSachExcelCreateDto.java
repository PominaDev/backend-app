package com.pomina.webapp.pricing_policy_management.dto.excel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChinhSachExcelCreateDto {

    @NotNull
    private Header header;

    @NotNull
    private List<Body> body;
}
