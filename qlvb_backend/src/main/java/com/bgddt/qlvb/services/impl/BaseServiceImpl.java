package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    JpaRepository<T, Long> repository;

    protected BaseServiceImpl(JpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T findById(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Not found"));
    }

    @Override
    @Transactional
    public T create(@Valid T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public T update(Long id, @Valid T entity) {
        return repository.save(entity);
    }
}
