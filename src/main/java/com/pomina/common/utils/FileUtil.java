package com.pomina.common.utils;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class FileUtil {

    private FileUtil() {}

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; //5MB

    private static final Set<String> WHITE_LIST_FILE_EXTENSION = Set.of("xls", "xlsx");

    public static void validateExcelFile(MultipartFile file) {
        // Check empty
        if (file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_IS_EMPTY);
        }

        // Check size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new AppException(ErrorCode.FILE_TOO_LARGE);
        }

        // Check extension
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new AppException(ErrorCode.INVALID_FILE_TYPE);
        }

        // lấy phần đuôi sau dấu chấm cuối
        String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        if (!WHITE_LIST_FILE_EXTENSION.contains(ext)) {
            throw new AppException(ErrorCode.INVALID_FILE_TYPE);
        }

        // Check content type
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/vnd.ms-excel") ||
                        contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
            //            throw new AppException(ErrorCode.INVALID_FILE_TYPE);
        }
    }
}
