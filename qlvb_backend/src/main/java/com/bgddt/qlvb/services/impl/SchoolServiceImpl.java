package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.entities.School;
import com.bgddt.qlvb.repositories.SchoolRepository;
import com.bgddt.qlvb.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolServiceImpl extends BaseServiceImpl implements SchoolService {
    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        super(schoolRepository, School.class);
    }
}
