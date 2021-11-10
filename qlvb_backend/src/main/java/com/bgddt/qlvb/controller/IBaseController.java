package com.bgddt.qlvb.controller;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<O> {
    ResponseEntity<List<O>> findAll();
    ResponseEntity<List<O>> findAllByPagination(Pageable pageable);
    ResponseEntity<O> findById(Long id) throws BusinessException;
    ResponseEntity<O> create(O dto) throws BusinessException;
    ResponseEntity<O> update(Long id,O dto) throws BusinessException;
    ResponseEntity<Long> delete(Long id);
}
