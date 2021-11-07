package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.entities.Clazz;
import com.bgddt.qlvb.repositories.ClazzRepository;
import com.bgddt.qlvb.services.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl extends BaseServiceImpl implements ClazzService {
    ClazzRepository clazzRepository;

    @Autowired
    protected ClazzServiceImpl(ClazzRepository repository) {
        super(repository, Clazz.class);
        this.clazzRepository = repository;
    }
}
