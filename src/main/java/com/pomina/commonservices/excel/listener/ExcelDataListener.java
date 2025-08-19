package com.pomina.commonservices.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public abstract class ExcelDataListener<T> extends AnalysisEventListener<T> {

    private List<T> dataList = new ArrayList<>();

    private List<String> errorMessages = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        try {
            // Validate data if needed
            validateData(data, context.readRowHolder().getRowIndex());
            dataList.add(data);
        } catch (Exception e) {
            String error = String.format("Row %d error: %s",
                    context.readRowHolder().getRowIndex() + 1, e.getMessage());
            errorMessages.add(error);
            log.warn(error);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("Excel reading completed. Total rows: {}, Errors: {}",
                dataList.size(), errorMessages.size());
    }

    /**
     * Hàm dùng để thêm lớp validate dữ liệu truyền vào khi upload file.
     * Khi cần validate -> @Override
     *
     * @param data dữ liệu cần validate
     * @param rowIndex số thứ tự dòng dữ liệu
     */
    protected void validateData(T data, Integer rowIndex) {}
}
