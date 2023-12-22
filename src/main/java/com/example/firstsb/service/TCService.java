package com.example.firstsb.service;

import com.example.firstsb.lib.CustomException.FatalException;
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
        Teacher teacher = teacherRepository.findById(tid)
                .orElseThrow(() -> new FatalException("教师不存在,id: " + tid));
        Course course = courseRepository.findById(cid)
                .orElseThrow(() -> new FatalException("课程不存在,id: " + cid));
        TC tc = new TC();
        tc.setTeacher(teacher);
        tc.setCourse(course);
        try{
            return tcRepository.save(tc);
        } catch (Exception e) {
            throw new FatalException("已经存在该选课记录");
        }
    }
    //del
    public void deleteById(int id) {
        tcRepository.deleteById(id);
    }
}
