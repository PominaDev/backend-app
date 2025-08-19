package com.pomina.commonservices.excel.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportErrorDetail {

    private Integer rowNumber;

    private String field;

    private String errorMessage;

    private Object actualValue;

    private Object expectedValue;

    private String errorCode;

    public static ImportErrorDetail of(int rowNumber, String field, String errorMessage) {
        return ImportErrorDetail.builder()
                .rowNumber(rowNumber)
                .field(field)
                .errorMessage(errorMessage)
                .build();
    }

    public static ImportErrorDetail of(int rowNumber, String field, String errorMessage,
                                       Object actualValue) {
        return ImportErrorDetail.builder()
                .rowNumber(rowNumber)
                .field(field)
                .errorMessage(errorMessage)
                .actualValue(actualValue)
                .build();
    }
}
