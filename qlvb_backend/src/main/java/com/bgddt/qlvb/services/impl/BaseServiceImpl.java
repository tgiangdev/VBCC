package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.services.BaseService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class BaseServiceImpl<O, T> implements BaseService<O, T> {
    JpaRepository<T, Long> repository;
    private Class<O> classOfDTO;  // Class của DTO
    private Class<T> classOfEntity;  // Class của Entity

    protected BaseServiceImpl(JpaRepository repository, Class<O> classOfO, Class<T> classOfT) {
        this.repository = repository;
        this.classOfDTO = classOfO;
        this.classOfEntity = classOfT;
    }

    protected final Gson GSON = new Gson();
    protected final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    protected final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<O> findAll() {
        List<T> entities = repository.findAll();
        return entities.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public Page<O> findAll(Pageable pageable) {
        Page<T> entityPages = repository.findAll(pageable);
        Page<O> dtoPages = entityPages.map((entity) -> entityToDto(entity));
        return dtoPages;
    }

    @Override
    public O findById(Long id) throws BusinessException {
        T entity = repository.findById(id).orElseThrow(() -> new BusinessException("Not found"));
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public O create(@Valid O dto) throws BusinessException {
        dto = objectToDto(dto); // Chuyển kiểu dữ liệu của dto về class của DTO

        dto = validateCreate(dto);
        T entity = dtoToEntity(dto);
        return entityToDto(repository.save(entity));
    }

    protected O validateCreate(O dto) {
        // Sử dụng thì chuyển Object sang classOfDto
        // VD: AccountDTO accountDto = (AccountDTO) dto;
        return dto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public O update(Long id, @Valid O dto) throws BusinessException {
        dto = objectToDto(dto); // Chuyển kiểu dữ liệu của dto về class của DTO

        dto = validateUpdate(id, dto);
        setId(dto, id);
        T entity = dtoToEntity(dto);
        return entityToDto(repository.save(entity));
    }

    protected <O> O validateUpdate(Long id, @Valid O dto) throws BusinessException {
        // Sử dụng thì chuyển Object sang classOfDto
        // VD: AccountDTO accountDto = (AccountDTO) dto;
        return dto;
    }

    private void setId(O dto, Long id) {
        try {
            Field field = dto.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(dto, id);
        }catch (Exception e) {
            // cannot read id field
        }
    }

    protected O objectToDto(O dto) {
        return MAPPER.convertValue(dto, classOfDTO);
    }
    protected T dtoToEntity(O dto) {
        return modelMapper.map(dto, classOfEntity);
    }
    protected O entityToDto(T entity) {
        return modelMapper.map(entity, classOfDTO);
    }
}
