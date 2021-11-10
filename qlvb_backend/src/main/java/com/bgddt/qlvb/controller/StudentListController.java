package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.StudentListDTO;
import com.bgddt.qlvb.services.StudentListService;
import com.bgddt.qlvb.utils.PaginationUtil;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Tag(name = "StudentList")
@RestController
@RequestMapping("/api/student-list")
public class StudentListController implements IBaseController<StudentListDTO> {
    @Autowired
    private StudentListService service;

    @Operation(summary = "Import excel student", operationId = "importExcel")
    @PostMapping(value = "import")
    public ResponseEntity importExcel(@RequestBody MultipartFile file, @RequestParam("info") String info) throws IOException, BusinessException {
        Gson gson = new Gson();
        StudentListDTO importRequest = gson.fromJson(info, StudentListDTO.class);
        return ResponseEntity.ok().body(service.importExcel(file, importRequest));
    }

    @Override
    @Operation(summary = "Get all", description = "", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<StudentListDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    @Operation(summary = "Get all by pagination", operationId = "findAllByPagination")
    @GetMapping("pagination")
    public ResponseEntity<List<StudentListDTO>> findAllByPagination(Pageable pageable) {
        Page<StudentListDTO> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<StudentListDTO> dtos = page.getContent();
        return new ResponseEntity<>(dtos, headers, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get by id", operationId = "findById")
    @GetMapping("{id}")
    public ResponseEntity<StudentListDTO> findById(@PathVariable Long id) throws BusinessException {
        return new ResponseEntity(service.findById(id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Create", operationId = "create")
    @PostMapping()
    public ResponseEntity<StudentListDTO> create(@RequestBody StudentListDTO dto) throws BusinessException {
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update", operationId = "update")
    @PutMapping("{id}")
    public ResponseEntity<StudentListDTO> update(@PathVariable Long id, @RequestBody StudentListDTO dto) throws BusinessException {
        return new ResponseEntity(service.update(id, dto), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Delete by id", operationId = "delete")
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }
}
