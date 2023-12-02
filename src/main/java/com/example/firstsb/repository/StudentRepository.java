package com.example.firstsb.repository;

import com.example.firstsb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    List<Student> findByNameContainingIgnoreCase(String name);
}
