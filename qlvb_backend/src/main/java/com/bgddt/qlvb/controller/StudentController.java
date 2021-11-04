package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import com.bgddt.qlvb.services.StudentService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Student")
@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Operation(summary = "Import excel student", operationId = "importExcel")
    @PostMapping(value = "import")
    public ResponseEntity importExcel(@RequestBody MultipartFile file, @RequestParam("info") String info) throws IOException, BusinessException {
        Gson gson = new Gson();
        ImportRequest importRequest = gson.fromJson(info, ImportRequest.class);
        return ResponseEntity.ok().body(studentService.importExcel(file, importRequest));
    }

    @Operation(summary = "Get all student", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @GetMapping("/sleep")
    public ResponseEntity<String> sleep(@RequestParam Long sleep) throws InterruptedException {
        Thread.sleep(sleep);
        return ResponseEntity.ok().body(String.format("{}"));
    }
}
