package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.StudentDTO;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService<O, T> extends BaseService<O, T> {
    List<Student> findAllByStudentListId(Long studentListId) throws BusinessException;
    List<StudentDTO> createAll(List<StudentDTO> dtos);
}
