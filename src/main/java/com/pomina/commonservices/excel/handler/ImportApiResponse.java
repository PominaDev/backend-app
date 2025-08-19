package com.pomina.commonservices.excel.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportApiResponse<T> {

    private boolean success;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    private ImportData<T> data;

    private LocalDateTime timestamp;

    public ImportApiResponse(boolean success, String message, String code, ImportData<T> data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public ImportApiResponse(boolean success, String message, ImportData<T> data) {
        this(success, message, null, data);
    }

    public ImportApiResponse(boolean success, String message) {
        this(success, message, null, null);
    }

    public static <T> ImportApiResponse<T> success(String message, ImportData<T> data) {
        return new ImportApiResponse<>(true, message, data);
    }

    public static <T> ImportApiResponse<T> success(String message, int successCount, int totalCount) {
        ImportData<T> data = ImportData.<T>builder()
                .successCount(successCount)
                .totalCount(totalCount)
                .errorCount(totalCount - successCount)
                .build();
        return new ImportApiResponse<>(true, message, data);
    }

    public static <T> ImportApiResponse<T> failure(String message, String code) {
        return new ImportApiResponse<>(false, message, code, null);
    }

    public static <T> ImportApiResponse<T> failure(String message, List<String> errors) {
        ImportData<T> data = ImportData.<T>builder()
                .errors(errors)
                .errorCount(errors.size())
                .build();
        return new ImportApiResponse<>(false, message, data);
    }

    public static <T> ImportApiResponse<T> failure(String message, ImportData<T> importData) {
        ImportData<T> data = ImportData.<T>builder()
                .errors(importData.getErrors())
                .errorCount(importData.getErrorDetails().size())
                .build();
        return new ImportApiResponse<>(false, message, data);

    }

    public static <T> ImportApiResponse<T> partialSuccess(String message, ImportData<T> data) {
        return new ImportApiResponse<>(data.getErrorCount() == 0, message, data);
    }
}
