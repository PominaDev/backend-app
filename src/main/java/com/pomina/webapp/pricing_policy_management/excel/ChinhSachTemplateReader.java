package com.pomina.webapp.pricing_policy_management.excel;

import com.alibaba.excel.util.StringUtils;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelBody;
import com.pomina.webapp.pricing_policy_management.dto.request.excel.ChinhSachExcelHeader;
import com.pomina.webapp.pricing_policy_management.dto.response.ChinhSachGiaBanTemplateDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ChinhSachTemplateReader {

    /**
     * Đọc file - Preview
     *
     * @param file file excel
     * @param maxBodyRows số dòng tối đa ở body
     * @return {@link ChinhSachGiaBanTemplateDto} - Trả về nội dung của file excel
     */
    public ChinhSachGiaBanTemplateDto readTemplate(MultipartFile file, int maxBodyRows) {
        try (FileInputStream fis = new FileInputStream(convertMultipartFileToFile(file));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Read body
            ChinhSachExcelBodyTemplate bodyTemplate = readBody(sheet, maxBodyRows);
            List<ChinhSachExcelBody> body = bodyTemplate.getChinhSachBody();

            // Read header
            ChinhSachExcelHeader header = readHeader(sheet, bodyTemplate.getLastRowIndex());

            return ChinhSachGiaBanTemplateDto.builder()
                    .header(header)
                    .body(body)
                    .build();

        } catch (Exception e) {
            throw new AppException(ErrorCode.FILE_PROCESSING_ERROR);
        }
    }

    private ChinhSachExcelHeader readHeader(Sheet sheet, int lastRowIndexBody) {
        try {
            return ChinhSachExcelHeader.builder()
                    .tenChinhSach(getCellValueAsString(sheet, 0, 1))
                    .ngayBatDau(getCellValueAsString(sheet, 1, 1))
                    .ngayKetThuc(getCellValueAsString(sheet, 2, 1))
                    .phongBan(getCellValueAsString(sheet, 4, 1))
                    .nguoiLap(getCellValueAsString(sheet, 5, 1))
                    .description(readDescription(sheet, lastRowIndexBody, 2))
                    .build();
        } catch (Exception e) {
            throw new AppException(ErrorCode.FILE_PROCESSING_ERROR);
        }
    }

    private ChinhSachExcelBodyTemplate readBody(Sheet sheet, int maxRows) {
        List<ChinhSachExcelBody> products = new ArrayList<>();
        int startRow = 8;
        int currentCount = 0;

        Iterator<Row> rowIterator = sheet.iterator();
        int currentRowIndex = 0;
        boolean previousWasData = false;
        int lastRowIndex = startRow;

        while (rowIterator.hasNext() && currentCount < maxRows) {
            Row row = rowIterator.next();

            if (currentRowIndex++ < startRow) {
                continue;
            }

            if (isEmptyRow(row)) {
                if (previousWasData) {
                    break; // kết thúc body
                } else {
                    continue;
                }
            }

            try {
                ChinhSachExcelBody product = readProductFromRow(row);
                if (product != null) {
                    products.add(product);
                    currentCount++;
                    previousWasData = true;
                    lastRowIndex = row.getRowNum();
                }
            } catch (Exception e) {
                ChinhSachExcelBody errorProduct = ChinhSachExcelBody.builder()
                        .tenSanPham("Error reading row")
                        .build();
                errorProduct.addWarning("Error reading row: " + e.getMessage());
                products.add(errorProduct);
                currentCount++;
                previousWasData = true;
                lastRowIndex = row.getRowNum();
            }
        }

        return new ChinhSachExcelBodyTemplate(products, lastRowIndex);
    }

    /**
     * Description: Gán giá trị các ô vào các trường trong ChinhSachExcelBody
     *
     * @param row dòng dữ liệu
     * @return {@link ChinhSachExcelBody}
     */
    private ChinhSachExcelBody readProductFromRow(Row row) {
        ChinhSachExcelBody product = ChinhSachExcelBody.builder()
                .tenSanPham(getCellValueAsString(row, 1))
                .size(getCellValueAsDouble(row, 2))
                .giaBang(getCellValueAsDouble(row, 3))
                .ckThanhToan(getCellValueAsDouble(row, 4))
                .csTrongThang(getCellValueAsDouble(row, 5))
                .build();

        // Read ck_cs_1 to ck_cs_10 (columns 6-16)
        product.setCkCs1(getCellValueAsDouble(row, 6));
        product.setCkCs2(getCellValueAsDouble(row, 7));
        product.setCkCs3(getCellValueAsDouble(row, 8));
        product.setCkCs4(getCellValueAsDouble(row, 9));
        product.setCkCs5(getCellValueAsDouble(row, 10));
        product.setCkCs6(getCellValueAsDouble(row, 11));
        product.setCkCs7(getCellValueAsDouble(row, 12));
        product.setCkCs8(getCellValueAsDouble(row, 13));
        product.setCkCs9(getCellValueAsDouble(row, 14));
        product.setCkCs10(getCellValueAsDouble(row, 15));
        product.setGiaMet(getCellValueAsDouble(row, 16));

        // Basic validation and warnings
        addValidationWarnings(product);

        return product;
    }

    private String readDescription(Sheet sheet, int lastRowIndexBody, int colCount) {
        StringBuilder sb = new StringBuilder();
        DataFormatter formatter = new DataFormatter();

        int descStartRow = lastRowIndexBody + 4; // nhảy 4 dòng sau body
        int emptyCount = 0; // đếm số dòng trống liên tiếp

        for (int rowIndex = descStartRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                emptyCount++;
                if (emptyCount >= 2) break; // 2 dòng trống liên tiếp thì dừng
                else continue;
            }

            StringBuilder line = new StringBuilder();
            boolean allEmpty = true;

            for (int col = 0; col < colCount; col++) {
                Cell cell = row.getCell(col);
                String value = (cell != null) ? formatter.formatCellValue(cell) : "";
                if (!value.isBlank()) {
                    if (line.length() > 0) line.append(" | "); // ngăn cách 2 cột
                    line.append(value.trim());
                    allEmpty = false;
                }
            }

            if (allEmpty) {
                emptyCount++;
                if (emptyCount >= 2) break;
            } else {
                emptyCount = 0; // reset vì dòng này có dữ liệu
                sb.append(line).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private void addValidationWarnings(ChinhSachExcelBody product) {
        if (StringUtils.isEmpty(product.getTenSanPham())) {
            product.addWarning("Tên sản phẩm không được để trống");
        }

        if (product.getGiaBang() != null && product.getGiaBang() < 0) {
            product.addWarning("Giá bảng không được âm");
        }

        if (product.getSize() != null && product.getSize() <= 0) {
            product.addWarning("Size phải lớn hơn 0");
        }
    }

    private String getCellValueAsString(Sheet sheet, int rowIndex, int colIndex) {
        try {
            Row row = sheet.getRow(rowIndex);
            if (row == null) return null;

            Cell cell = row.getCell(colIndex);
            return getCellValueAsString(cell);
        } catch (Exception e) {
            return null;
        }
    }

    private String getCellValueAsString(Row row, int colIndex) {
        try {
            Cell cell = row.getCell(colIndex);
            return getCellValueAsString(cell);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Hàm chuyển đổi kiểu dữ liệu về String
     *
     * @param cell ô dữ liệu
     * @return giá trị kiểu String
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                DataFormatter formatter = new DataFormatter();
                return formatter.formatCellValue(cell);
            default:
                return null;
        }
    }

    /**
     * Hàm chuyển đổi kiểu dữ liệu về Double
     *
     * @param row      dòng dữ liệu
     * @param colIndex vị trí của cột
     * @return giá trị kiểu String
     */
    private Double getCellValueAsDouble(Row row, int colIndex) {
        try {
            Cell cell = row.getCell(colIndex);
            if (cell == null) return null;

            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                String value = cell.getStringCellValue().trim();
                return StringUtils.isEmpty(value) ? null : Double.parseDouble(value);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description: Kiểm tra dòng có giá trị rỗng hay không ?
     *
     * @param row dòng dữ liệu
     * @return boolean
     */
    private boolean isEmptyRow(Row row) {
        if (row == null) return true;

        Cell firstCell = row.getCell(0);
        if (firstCell == null) return true;

        String value = getCellValueAsString(firstCell);
        return StringUtils.isEmpty(value);
    }

    /**
     * Description: Hàm chuyển định sang multipartFile thành File
     *
     * @param multipartFile file truyền vào
     * @return file định dạng File
     * @throws Exception
     */
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File tempFile = File.createTempFile("temp", ".xlsx");
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}
