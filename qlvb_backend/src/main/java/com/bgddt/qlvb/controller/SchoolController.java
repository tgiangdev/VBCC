package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.dtos.SchoolDTO;
import com.bgddt.qlvb.entities.School;
import com.bgddt.qlvb.services.SchoolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "School")
@RestController
@RequestMapping("/api/school")
public class SchoolController extends BaseController<SchoolDTO, School> {
    SchoolService schoolService;

    @Autowired
    SchoolController(SchoolService service) {
        super(service);
        this.schoolService = service;
    }

    @Override
    public void generateDto(SchoolDTO dto) {

    }
}
