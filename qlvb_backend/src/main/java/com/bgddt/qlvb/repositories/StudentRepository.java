package com.bgddt.qlvb.repositories;

import com.bgddt.qlvb.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByClazzId(Long clazzId);
}
