package com.example.firstsb.controller;

import com.example.firstsb.model.Teacher;
import com.example.firstsb.service.TeacherService;
import com.example.firstsb.lib.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<List<Teacher>>> findAllTeachers() {
        List<Teacher> teachers = teacherService.findAll();
        ResponseData<List<Teacher>> responseData = new ResponseData<>(teachers);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/latest")
    public ResponseEntity<ResponseData<List<Teacher>>> findLatestTeachers() {
        List<Teacher> teachers = teacherService.findLatestTeachers();
        ResponseData<List<Teacher>> responseData = new ResponseData<>(teachers);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Teacher>> findTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.findById(id);
        ResponseData<Teacher> responseData = new ResponseData<>(teacher);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseData<Teacher>> saveTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.save(teacher);
        ResponseData<Teacher> responseData = new ResponseData<>(savedTeacher);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteById(id);
        ResponseData<String> responseData = new ResponseData<>("成功删除教师,id: " + id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
