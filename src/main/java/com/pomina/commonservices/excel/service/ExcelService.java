package com.pomina.commonservices.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
public class ExcelService<T> {

    private final int BATCH_SIZE = 50;

    /**
     * Import Excel
     * @param inputStream
     * @param clazz
     * @param batchHandler
     * @throws AppException
     */
    @Async
    public void importExcel(
            InputStream inputStream,
            Class<T> clazz,
            Consumer<List<T>> batchHandler) throws AppException {

        AnalysisEventListener<T> listener = new AnalysisEventListener<T>() {
            private final List<T> cachedList = new ArrayList<>(BATCH_SIZE);

            @Override
            public void invoke(T data, AnalysisContext context) {
                cachedList.add(data);
                if (cachedList.size() >= BATCH_SIZE) {
                    processBatch();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                if (!cachedList.isEmpty()) {
                    processBatch();
                }
                log.info("Completed processing {} rows", context.readRowHolder().getRowIndex());
            }

            private void processBatch() {
                try {
                    batchHandler.accept(new ArrayList<>(cachedList));
                } catch (Exception e) {
                    throw new AppException(ErrorCode.INTERNAL_ERROR);
                }
                cachedList.clear();
            }
        };

        try {
            EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
    }

    /**
     * Export excel
     * @param response
     * @param data
     * @param clazz
     * @param fileName
     * @throws IOException
     */
    public void exportExcel(
            HttpServletResponse response,
            List<T> data,
            Class<T> clazz,
            String fileName) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + ".xlsx");

        EasyExcel.write(response.getOutputStream(), clazz)
                .autoCloseStream(true)
                .sheet("Data")
                .doWrite(data);
    }
}
