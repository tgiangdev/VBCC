package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<Student> importExcel(MultipartFile file, ImportRequest importRequest) throws IOException, BusinessException;
    List<Student> findAll();
    List<Student> findAllByClazz(Long clazzId) throws BusinessException;
    Student findById(Long id);
    Student createStudent(Student student);
    boolean deleteStudent(Long id);
    Student updateStudent(Long id, Student student);
}
