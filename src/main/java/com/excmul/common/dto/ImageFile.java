package com.excmul.common.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImageFile extends AbstractFile {
    private static final String IMAGE_STORAGE_PATH = "src/main/resources/static/image/";

    public ImageFile(MultipartFile multipartFile) throws IOException {
        super(multipartFile);
    }

    @Override
    public void saveFileToLocal() throws IOException {
        String fullFileName = IMAGE_STORAGE_PATH + File.separator + getUniqueFileName();
        FileOutputStream fileOutputStream = new FileOutputStream(fullFileName);

        int data = 0;
        byte[] buffer = new byte[1024];
        while ((data = getInputStream().read(buffer, 0, 1024)) != -1) {
            fileOutputStream.write(buffer, 0, data);
        }
    }
}
