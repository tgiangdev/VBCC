package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    T findById(Long id) throws BusinessException;
    T create(T entity);
    void delete(Long id);
    T update(Long id, T entity);
}
