package com.excmul.common.utils;

import java.io.*;
import java.util.Calendar;

public class FileManager {
    public static final String ABSOLUTE_ADDRESS_DIR = "src/main/resources/static/image/";
    public static final String RELATIVE_ADDRESS_DIR = "../image/";

    public static String doFileUpload(InputStream inputStream,
                                      String originalFileName,
                                      String path) {
        String newFileName = null;
        try {
            // 파일 이름이 비었는지
            validateEmptyFileName(originalFileName);

            // 파일 확장자가 있는지
            String fileExtensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            validateFileFormat(fileExtensionName);

            // 중복되지 않도록 파일명 변경
            newFileName = String.format(
                    "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS",
                    Calendar.getInstance()
            );
            newFileName += System.nanoTime();
            newFileName += fileExtensionName;

            // 디렉토리 위치
            File file = new File(path);
            validateExistDirectory(file);

            // "파일위치 + 파일명 합쳐서" 저장
            String fullFileName = path + File.separator + newFileName;
            FileOutputStream fileOutputStream = new FileOutputStream(fullFileName);

            // 파일 데이터 저장
            int data = 0;
            byte[] buffer = new byte[1024];
            while ((data = inputStream.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, data);
            }

            // 자원 해제
            inputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    private static void validateExistDirectory(File file) throws IOException {
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("디렉토리가 존재하지 않습니다.");
        }
    }

    private static void validateFileFormat(String fileExt) throws IOException {
        if (fileExt == null || fileExt.equals("")) {
            throw new IOException("파일 명의 형식이 이상합니다.");
        }
    }

    private static void validateEmptyFileName(String originalFileName) throws IOException {
        if (originalFileName.equals("")) {
            throw new IOException("파일 명이 없습니다.");
        }
    }
}
