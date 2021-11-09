package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BaseService<O, T> {
    List<O> findAll();
    Page<O> findAll(Pageable pageable);
    O findById(Long id) throws BusinessException;
    O create(O entity) throws BusinessException;
    void delete(Long id);
    O update(Long id, O entity) throws BusinessException;
}
