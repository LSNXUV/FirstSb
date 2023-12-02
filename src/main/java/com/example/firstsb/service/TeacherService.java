package com.example.firstsb.service;

import com.example.firstsb.model.Teacher;
import com.example.firstsb.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    //findAll
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    public List<Teacher> findLatestTeachers() {
        return teacherRepository.findLatestTeachers();
    }
    //findById
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }
    //add or update
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    //del
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }
}
