package com.example.firstsb.service;

import com.example.firstsb.model.Course;
import com.example.firstsb.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //findAll
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
    //findByName
    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    //add or update
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    //del
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

}
