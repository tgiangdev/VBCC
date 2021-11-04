package com.bgddt.qlvb.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImportRequest {
    private String clazz;
    private String year;
//    private MultipartFile file;
}
