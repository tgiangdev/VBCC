package com.bgddt.qlvb.services;

import com.bgddt.qlvb.common.exceptions.BusinessException;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    T findById(Long id) throws BusinessException;
    T create(T entity);
    void delete(Long id);
    T update(Long id, T entity);
}
