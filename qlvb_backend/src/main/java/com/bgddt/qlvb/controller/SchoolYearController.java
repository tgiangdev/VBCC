package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.SchoolYear;
import com.bgddt.qlvb.services.SchoolYearService;
import com.bgddt.qlvb.utils.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "SchoolYear")
@RestController
@RequestMapping("/api/school-year")
public class SchoolYearController implements IBaseController<SchoolYear> {
    @Autowired
    private SchoolYearService service;
    
    @Override
    @Operation(summary = "Get all", description = "", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<SchoolYear>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    @Operation(summary = "Get all by pagination", operationId = "findAllByPagination")
    @GetMapping("pagination")
    public ResponseEntity<List<SchoolYear>> findAllByPagination(Pageable pageable) {
        Page<SchoolYear> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<SchoolYear> dtos = page.getContent();
        return new ResponseEntity<>(dtos, headers, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get by id", operationId = "findById")
    @GetMapping("{id}")
    public ResponseEntity<SchoolYear> findById(@PathVariable Long id) throws BusinessException {
        return new ResponseEntity(service.findById(id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Create", operationId = "create")
    @PostMapping()
    public ResponseEntity<SchoolYear> create(@RequestBody SchoolYear dto) throws BusinessException {
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update", operationId = "update")
    @PutMapping("{id}")
    public ResponseEntity<SchoolYear> update(@PathVariable Long id, @RequestBody SchoolYear dto) throws BusinessException {
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
