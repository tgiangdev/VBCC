package com.bgddt.qlvb.repositories;

import com.bgddt.qlvb.entities.SharedDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedDirectoryRepository extends JpaRepository<SharedDirectory, Long> {
}
