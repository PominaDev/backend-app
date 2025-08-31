package com.pomina.commonservices.upload_file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    String uploadImage(MultipartFile file) throws IOException;
}
