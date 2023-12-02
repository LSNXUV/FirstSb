package com.example.firstsb.service;

import com.example.firstsb.model.Course;
import com.example.firstsb.model.TC;
import com.example.firstsb.model.Teacher;
import com.example.firstsb.repository.CourseRepository;
import com.example.firstsb.repository.TCRepository;
import com.example.firstsb.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCService {
    private final TCRepository tcRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    @Autowired
    public TCService(TCRepository tcRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.tcRepository = tcRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }
    //findAll
    public List<TC> findAll() {
        return tcRepository.findAll();
    }

    //add or save
    public TC save(Long tid,Long cid) {
        Teacher teacher = teacherRepository.findById(tid).orElse(null);
        if(teacher == null) {
            return null;
        }
        Course course = courseRepository.findById(cid).orElse(null);
        if(course == null) {
            return null;
        }
        TC tc = new TC();
        tc.setTeacher(teacher);
        tc.setCourse(course);
        return tcRepository.save(tc);
    }
    //del
    public void deleteById(int id) {
        tcRepository.deleteById(id);
    }
}
