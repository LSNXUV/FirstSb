package com.example.firstsb.controller;

import com.example.firstsb.model.Course;
import com.example.firstsb.service.CourseService;
import com.example.firstsb.lib.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("all")
    public Response<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        return Response.success(courses);
    }

    @GetMapping("/search")
    public Response<Course> getCourseByName(@RequestParam String name) {
        if(name == null || name.isEmpty()) {
            return Response.error("name不能为空");
        }
        Course courses = courseService.findByName(name);
        return Response.success(courses);
    }

    @PostMapping("/save")
    public Response<Course> saveCourse(@RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        return Response.success(savedCourse);
    }

    @DeleteMapping("/delete")
    public Response<String> deleteById(@RequestParam Long id) {
        courseService.deleteById(id);
        return Response.successM("成功删除课程,id: " + id);
    }

}
