package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.SharedDirectory;
import com.bgddt.qlvb.services.SharedDirectoryService;
import com.bgddt.qlvb.services.StudentListService;
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

@Tag(name = "SharedDirectory")
@RestController
@RequestMapping("/api/shared-directory")
public class SharedDirectoryController implements IBaseController<SharedDirectory> {
    @Autowired
    private SharedDirectoryService service;

    @Override
    @Operation(summary = "Get all", description = "", operationId = "findAll")
    @GetMapping()
    public ResponseEntity<List<SharedDirectory>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    @Operation(summary = "Get all by pagination", operationId = "findAllByPagination")
    @GetMapping("pagination")
    public ResponseEntity<List<SharedDirectory>> findAllByPagination(Pageable pageable) {
        Page<SharedDirectory> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<SharedDirectory> dtos = page.getContent();
        return new ResponseEntity<>(dtos, headers, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get by id", operationId = "findById")
    @GetMapping("{id}")
    public ResponseEntity<SharedDirectory> findById(@PathVariable Long id) throws BusinessException {
        return new ResponseEntity(service.findById(id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Create", operationId = "create")
    @PostMapping()
    public ResponseEntity<SharedDirectory> create(@RequestBody SharedDirectory dto) throws BusinessException {
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update", operationId = "update")
    @PutMapping("{id}")
    public ResponseEntity<SharedDirectory> update(@PathVariable Long id, @RequestBody SharedDirectory dto) throws BusinessException {
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
