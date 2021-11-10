package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.entities.SharedDirectory;
import com.bgddt.qlvb.repositories.SharedDirectoryRepository;
import com.bgddt.qlvb.services.SharedDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharedDirectoryServiceImpl extends BaseServiceImpl<SharedDirectory, SharedDirectory> implements SharedDirectoryService<SharedDirectory, SharedDirectory>  {
    @Autowired
    protected SharedDirectoryServiceImpl(SharedDirectoryRepository repository) {
        super(repository, SharedDirectory.class, SharedDirectory.class);
    }
}
