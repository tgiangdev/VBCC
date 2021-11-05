package com.bgddt.qlvb.repositories;

import com.bgddt.qlvb.entities.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
}
