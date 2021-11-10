package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.StudentDTO;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import com.bgddt.qlvb.repositories.StudentRepository;
import com.bgddt.qlvb.services.StudentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, Student> implements StudentService<StudentDTO, Student> {
    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository bookRepository) {
        super(bookRepository, StudentDTO.class, Student.class);
        this.studentRepository = bookRepository;
    }

    @Override
    public List<Student> findAllByStudentListId(Long studentListId) throws BusinessException {
        // TODO: viết tiếp
        return null;
    }

    @Override
    public List<StudentDTO> createAll(List<StudentDTO> dtos) {
        List<Student> students = dtos.stream().map(d -> dtoToEntity(d)).collect(Collectors.toList());
        students = studentRepository.saveAll(students);
        return students.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
    }
}
