package com.example.firstsb.service;

import com.example.firstsb.model.Course;
import com.example.firstsb.model.SC;
import com.example.firstsb.model.Student;
import com.example.firstsb.repository.CourseRepository;
import com.example.firstsb.repository.SCRepository;
import com.example.firstsb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SCService {
    private final SCRepository scRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SCService(SCRepository scRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.scRepository = scRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    //findAll
    public List<SC> findAll() {
        return scRepository.findAll();
    }

    //add or save
    public SC save(Long cid, Long sid) {

        Course course = courseRepository.findById(cid).orElse(null);

        Student student = studentRepository.findById(sid).orElse(null);

        if(course == null || student == null) {
            return null;
        }
        // Create an SC object and set the Course and Student
        SC sc = new SC();
        sc.setCourse(course);
        sc.setStudent(student);

        return scRepository.save(sc);
    }
    //del
    public void deleteById(int id) {
        scRepository.deleteById(id);
    }
}
