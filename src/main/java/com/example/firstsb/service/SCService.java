package com.example.firstsb.service;

import com.example.firstsb.lib.CustomException.FatalException;
import com.example.firstsb.model.Course;
import com.example.firstsb.model.SC;
import com.example.firstsb.model.Student;
import com.example.firstsb.repository.CourseRepository;
import com.example.firstsb.repository.SCRepository;
import com.example.firstsb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 选课,根据课程id和学生id
     * @param cid
     * @param sid
     * @return
     */
    public SC save(Long cid, Long sid) {
        Course course = courseRepository.findById(cid)
                .orElseThrow(() -> new FatalException("课程不存在,id: " + cid));
        Student student = studentRepository.findById(sid)
                .orElseThrow(() -> new FatalException("学生不存在,id: " + sid));

        SC sc = new SC();
        sc.setCourse(course);
        sc.setStudent(student);
        try{
            return scRepository.save(sc);
        } catch (Exception e) {
            throw new FatalException("已经存在该选课记录");
        }
    }

    /**
     *  根据id和分数修改选课记录
     * @param id
     * @param score
     * @return
     */
    public SC save(int id, int score) {
        SC sc = scRepository.findById(id)
                .orElseThrow(() -> new FatalException("不存在该选课记录"));
        sc.setScore(score);
        return scRepository.save(sc);
    }

    /**
     * 根据id删除选课记录
     * @param id
     */
    public void deleteById(int id) {
        scRepository.deleteById(id);
    }
}
