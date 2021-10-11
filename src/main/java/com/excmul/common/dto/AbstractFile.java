package com.excmul.common.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;

public abstract class AbstractFile {
    private final InputStream inputStream;
    private final String originalFileName;

    private static final String FILE_NAME_SEPARATOR = ".";
    private static final String FORMAT = "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS";

    protected AbstractFile(MultipartFile multipartFile) throws IOException {
        this(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
    }

    protected AbstractFile(InputStream inputStream, String originalFileName) throws IOException {
        validateEmptyFileName(originalFileName);
        validateFileFormat(originalFileName);

        this.inputStream = inputStream;
        this.originalFileName = originalFileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getUniqueFileName() {
        String newFileName;
        String fileExtensionName = originalFileName.substring(
                originalFileName.lastIndexOf(FILE_NAME_SEPARATOR)
        );
        newFileName = String.format(FORMAT, Calendar.getInstance());
        newFileName += System.nanoTime();
        newFileName += fileExtensionName;
        return newFileName;
    }

    abstract void saveFileToLocal() throws IOException;

    private void validateFileFormat(String fileExt) throws IOException {
        if (fileExt == null || fileExt.equals("")) {
            throw new IOException("파일 명의 형식이 이상합니다.");
        }
    }

    private void validateEmptyFileName(String originalFileName) throws IOException {
        if (originalFileName.equals("")) {
            throw new IOException("파일 명이 없습니다.");
        }
    }
}
