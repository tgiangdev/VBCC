package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.StudentDTO;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import com.bgddt.qlvb.services.StudentService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Student")
@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseController<StudentDTO, Student> {
    StudentService studentService;

    @Autowired
    StudentController(StudentService service) {
        super(service);
        this.studentService = service;
    }

    @Override
    public void generateDto(StudentDTO dto) {

    }
}
