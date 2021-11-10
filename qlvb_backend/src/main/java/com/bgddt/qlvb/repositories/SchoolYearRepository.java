package com.bgddt.qlvb.repositories;

import com.bgddt.qlvb.entities.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {
}
