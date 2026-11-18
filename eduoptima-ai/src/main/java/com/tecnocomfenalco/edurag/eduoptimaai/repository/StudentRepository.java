package com.tecnocomfenalco.edurag.eduoptimaai.repository;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
