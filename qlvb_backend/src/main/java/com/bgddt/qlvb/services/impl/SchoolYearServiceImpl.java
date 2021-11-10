package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.entities.SchoolYear;
import com.bgddt.qlvb.repositories.SchoolYearRepository;
import com.bgddt.qlvb.services.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolYearServiceImpl extends BaseServiceImpl<SchoolYear, SchoolYear> implements SchoolYearService<SchoolYear, SchoolYear> {
    @Autowired
    protected SchoolYearServiceImpl(SchoolYearRepository repository) {
        super(repository, SchoolYear.class, SchoolYear.class);
    }
}
