package com.pomina.commonservices.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.excel.config.ExcelConfig;
import com.pomina.commonservices.excel.handler.ImportApiResponse;
import com.pomina.commonservices.excel.handler.ImportData;
import com.pomina.commonservices.excel.handler.ImportErrorDetail;
import com.pomina.commonservices.excel.listener.ExcelDataListener;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelService {

    /**
     * Export data to Excel
     */
    public <T> void exportExcel(HttpServletResponse response,
                                List<T> dataList,
                                Class<T> clazz,
                                String fileName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");

            String encodedFileName = URLEncoder.encode(fileName, "UTF-8")
                    .replaceAll("\\+", "%20");
            response.setHeader("Content-disposition",
                    "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet("Sheet1")
                    .doWrite(dataList);

        } catch (Exception e) {
            log.error("Export Excel error: ", e);
            throw new AppException(ErrorCode.IMPORT_EXCEL_FAILED);
        }
    }

    /**
     * Import Excel with validation and processing
     */
    public <T> ImportApiResponse<T> importExcelWithValidation(
            MultipartFile file,
            Class<T> clazz,
            ExcelConfig<T> config) {

        long startTime = System.currentTimeMillis();
        String fileName = file.getOriginalFilename();

        ImportData<T> importData = ImportData.<T>builder()
                .fileName(fileName)
                .totalCount(0)
                .successCount(0)
                .errorCount(0)
                .errors(new ArrayList<>())
                .errorDetails(new ArrayList<>())
                .build();

        try {
            List<T> allData = new ArrayList<>();
            List<T> validData = new ArrayList<>();

            // Read Excel file
            ExcelDataListener<T> listener = new ExcelDataListener<T>() {
                @Override
                public void invoke(T data, AnalysisContext context) {
                    allData.add(data);
                    importData.setTotalCount(importData.getTotalCount() + 1);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("Excel reading completed. Total rows: {}", allData.size());
                }
            };

            EasyExcel.read(file.getInputStream(), clazz, listener)
                    .sheet()
                    .doRead();

            // Validation
            if (config.getValidator() != null && !allData.isEmpty()) {
                List<ImportErrorDetail> errorDetails = config.getValidator().apply(allData);
                importData.setErrorDetails(errorDetails);
                importData.setErrorCount(errorDetails.size());

                // Separate valid and invalid data
                Set<Integer> errorRows = errorDetails.stream()
                        .map(ImportErrorDetail::getRowNumber)
                        .collect(Collectors.toSet());

                for (int i = 0; i < allData.size(); i++) {
                    // +2 Vì dòng excel bắt đầu bằng 1
                    if (!errorRows.contains(i + 2)) {
                        validData.add(allData.get(i));
                    }
                }

                importData.setSuccessCount(validData.size());
                importData.setSuccessData(validData);
            } else {
                validData = allData;
                importData.setSuccessCount(allData.size());
                importData.setSuccessData(validData);
            }

            // Processing valid data
            if (config.getProcessor() != null && !validData.isEmpty()) {
                config.getProcessor().accept(validData);
            }

            // Set processing time
            long endTime = System.currentTimeMillis();
            importData.setProcessingTime((endTime - startTime) + "ms");

            // Determine success status and message
            boolean isSuccess = importData.getErrorCount() == 0;
            String message = isSuccess
                    ? String.format("Import completed successfully. %d records processed.", importData.getSuccessCount())
                    : String.format("Import completed with errors. %d successful, %d failed out of %d total records.",
                    importData.getSuccessCount(), importData.getErrorCount(), importData.getTotalCount());

            return ImportApiResponse.partialSuccess(message, importData);

        } catch (Exception e) {
            log.error("Import Excel error: ", e);
            long endTime = System.currentTimeMillis();
            importData.setProcessingTime((endTime - startTime) + "ms");
            importData.addError("File processing failed: " + e.getMessage());

            return ImportApiResponse.failure("Import failed due to system error", importData);
        }
    }

    /**
     * Description: Import Excel (Not validate data)
     * @param file
     * @param clazz
     * @param listener
     * @return
     * @param <T>
     */
    public <T> List<T> importExcelSimple(MultipartFile file,
                                         Class<T> clazz,
                                         ExcelDataListener<T> listener) {
        try {
            EasyExcel.read(file.getInputStream(), clazz, listener)
                    .sheet()
                    .doRead();
            return listener.getDataList();
        } catch (Exception e) {
            log.error("Simple import Excel error: ", e);
            throw new AppException(ErrorCode.IMPORT_EXCEL_FAILED);
        }
    }

    public <T> void exportExcelWithTemplate(HttpServletResponse response,
                                            List<T> dataList,
                                            String templatePath,
                                            String fileName,
                                            Map<String, Object> variables) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

            InputStream templateStream = this.getClass().getClassLoader().getResourceAsStream(templatePath);
            if (templateStream == null) {
                log.error("Excel file not found");
                throw new AppException(ErrorCode.EXCEL_FILE_NOT_FOUND);
            }

            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .withTemplate(templateStream)
                    .build();

            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            // Fill biến đơn lẻ
            if (variables != null && !variables.isEmpty()) {
                excelWriter.fill(variables, writeSheet);
            }

            // Fill list data với FillWrapper
            if (dataList != null && !dataList.isEmpty()) {
                excelWriter.fill(new FillWrapper("list", dataList), writeSheet);
            }

            excelWriter.finish();

        } catch (Exception e) {
            log.error("Export Excel with template error: ", e);
            throw new AppException(ErrorCode.EXPORT_EXCEL_FAILED);
        }
    }



}