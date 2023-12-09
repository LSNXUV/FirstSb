package com.example.firstsb.service;

import com.example.firstsb.model.Student;
import com.example.firstsb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //findAll
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Page<Student> findAll(Pageable pageable) {
        if (pageable == null) {
            // 返回所有学生
            List<Student> students = studentRepository.findAll();
            return new PageImpl<>(students);
        }
        return studentRepository.findAll(pageable);
    }

    //findByName，模糊查询
    public List<Student> findByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    //findById
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    //add or update
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    //del
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }


}
