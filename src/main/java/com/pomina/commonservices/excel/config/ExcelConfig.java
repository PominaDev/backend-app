package com.pomina.commonservices.excel.config;

import com.pomina.commonservices.excel.handler.ImportErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelConfig<T> {

    private Function<List<T>, List<ImportErrorDetail>> validator;

    private Consumer<List<T>> processor;

    private Integer batchSize = 50;

    private boolean skipErrorRows = true;

    private boolean continueOnError = true;

    public static <T> ExcelConfig<T> of(
            Function<List<T>, List<ImportErrorDetail>> validator,
            Consumer<List<T>> processor) {
        return ExcelConfig.<T>builder()
                .validator(validator)
                .processor(processor)
                .build();
    }
}
