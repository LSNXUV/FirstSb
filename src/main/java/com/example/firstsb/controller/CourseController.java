package com.example.firstsb.controller;

import com.example.firstsb.model.Course;
import com.example.firstsb.service.CourseService;
import com.example.firstsb.tool.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("all")
    public ResponseEntity<ResponseData<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        ResponseData<List<Course>> responseData = new ResponseData<>(courses);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<ResponseData<List<Course>>> getCourseByName(@PathVariable String name) {
        List<Course> courses = courseService.findByName(name);
        ResponseData<List<Course>> responseData = new ResponseData<>(courses);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseData<Course>> saveCourse(@RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        ResponseData<Course> responseData = new ResponseData<>(savedCourse);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        ResponseData<String> responseData = new ResponseData<>("成功删除课程,id: " + id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
