package com.bgddt.qlvb.repositories;

import com.bgddt.qlvb.entities.StudentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentListRepository extends JpaRepository<StudentList, Long> {
}
