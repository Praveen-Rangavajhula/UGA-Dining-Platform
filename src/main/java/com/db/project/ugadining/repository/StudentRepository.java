package com.db.project.ugadining.repository;

import com.db.project.ugadining.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByStudentName(String name);
    Boolean existsByStudentName(String name);
    void deleteByStudentName(String name);
    Optional<Student> findStudentByStudentEmail(String email);
}