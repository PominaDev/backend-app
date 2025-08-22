package com.pomina.commonservices.excel.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportData<T> {

    private Integer totalCount;

    private Integer successCount;

    private Integer errorCount;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ImportErrorDetail> errorDetails;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> successData;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> errorData;

    private String processingTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fileName;

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        this.errorCount = (this.errorCount == null ? 0 : this.errorCount) + 1;
    }

    public void addErrorDetail(ImportErrorDetail errorDetail) {
        if (this.errorDetails == null) {
            this.errorDetails = new ArrayList<>();
        }
        this.errorDetails.add(errorDetail);
    }

    public boolean hasErrors() {
        return errorCount != null && errorCount > 0;
    }

    public double getSuccessRate() {
        if (totalCount == null || totalCount == 0) return 0.0;
        return (double) (successCount == null ? 0 : successCount) / totalCount * 100;
    }
}


