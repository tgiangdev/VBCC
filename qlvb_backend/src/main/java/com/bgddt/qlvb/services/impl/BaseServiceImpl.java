package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.Account;
import com.bgddt.qlvb.services.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    JpaRepository<T, Long> repository;
    private Class<T> classOfT;

    protected BaseServiceImpl(JpaRepository repository, Class<T> classOfT) {
        this.repository = repository;
        this.classOfT = classOfT;
    }

    protected final Gson GSON = new Gson();
    protected ObjectMapper MAPPER = new ObjectMapper();

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
    public T create(@Valid T entity) throws BusinessException {
        entity = validateCreate(objectToEntity(entity));
        return repository.save(entity);
    }

    protected T validateCreate(T entity) {
        return entity;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public T update(Long id, @Valid T entity) throws BusinessException {
        entity = validateUpdate(id, objectToEntity(entity));
        setId(entity, id);
        return repository.save(entity);
    }

    protected <T> T validateUpdate(Long id, @Valid T entity) throws BusinessException {
        return entity;
    }

    private void setId(T entity, Long id) {
        try {
            Field field = entity.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, id);
        }catch (Exception e) {
            // cannot read id field
        }
    }

    protected T objectToEntity(T entity) {
        return MAPPER.convertValue(entity, classOfT);
    }
}
