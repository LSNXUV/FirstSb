package com.example.firstsb.repository;

import com.example.firstsb.model.Course;
import com.example.firstsb.model.SC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SCRepository extends JpaRepository<SC, Integer> {

    List<SC> findByCourse(Course c);
}
