package com.example.firstsb.repository;

import com.example.firstsb.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name); //根据课程名查找
}
