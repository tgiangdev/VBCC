package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.services.BaseService;
import com.bgddt.qlvb.utils.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

public class BaseController<O, T> {
    BaseService<O, T> service;
    BaseController(BaseService service) {
        this.service = service;
    }

    final String getNS() {
        return "";
    }

    @Operation(summary = "Get all", description = "", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<O>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(summary = "Get all by pagination", operationId = "findAllByPagination")
    @GetMapping("pagination")
    public ResponseEntity<List<O>> findAllByPagination(Pageable pageable) {
        Page<O> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<O> dtos = page.getContent();
        return new ResponseEntity<>(dtos, headers, HttpStatus.OK);
    }

    @Operation(summary = "Get by id", operationId = "findById")
    @GetMapping("{id}")
    public ResponseEntity<O> findById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Create", operationId = "create")
    @PostMapping()
    public ResponseEntity<O> create(@RequestBody O dto) throws BusinessException {
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update", operationId = "update")
    @PutMapping("{id}")
    public ResponseEntity<O> update(@PathVariable Long id, @RequestBody O dto) throws BusinessException {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete by id", operationId = "delete")
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }
}
