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

public class BaseController<T> {
    BaseService<T> service;
    BaseController(BaseService service) {
        this.service = service;
    }

    final String getNS() {
        return "";
    }

    @Operation(summary = "Get all", description = "", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(summary = "Get all by pagination", operationId = "findAllByPagination")
    @GetMapping("pagination")
    public ResponseEntity<List<T>> findAllByPagination(Pageable pageable) {
        Page<T> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Get by id", operationId = "findById")
    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Operation(summary = "Create", operationId = "create")
    @PostMapping()
    public ResponseEntity<T> create(@RequestBody T entity) throws BusinessException {
        return ResponseEntity.ok().body(service.create(entity));
    }

    @Operation(summary = "Update", operationId = "update")
    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entity) throws BusinessException {
        return ResponseEntity.ok().body(service.update(id, entity));
    }

    @Operation(summary = "Delete by id", operationId = "delete")
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
