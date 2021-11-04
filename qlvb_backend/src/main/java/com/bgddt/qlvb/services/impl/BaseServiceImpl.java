package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.repositories.BaseRepository;
import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    BaseRepository<T> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Not found"));
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public T update(Long id, T entity) {
        return repository.save(entity);
    }
}
