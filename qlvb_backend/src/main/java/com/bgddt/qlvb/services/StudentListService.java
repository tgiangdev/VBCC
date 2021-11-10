package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.StudentListDTO;
import com.bgddt.qlvb.entities.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentListService<O, T> extends BaseService<O, T>{
    StudentListDTO importExcel(MultipartFile file, StudentListDTO importRequest) throws IOException, BusinessException;
}
