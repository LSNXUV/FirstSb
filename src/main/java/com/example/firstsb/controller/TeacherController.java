package com.example.firstsb.controller;

import com.example.firstsb.model.Teacher;
import com.example.firstsb.service.TeacherService;
import com.example.firstsb.lib.Response;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public Response<List<Teacher>> findAllTeachers() {
        List<Teacher> teachers = teacherService.findAll();
        return Response.success(teachers, "获取成功");
    }
    @GetMapping("/latest")
    public Response<List<Teacher>> findLatestTeachers() {
        List<Teacher> teachers = teacherService.findLatestTeachers();
        return Response.success(teachers, "获取成功");
    }
    @GetMapping("/search")
    public Response<Teacher> findTeacherById(@RequestParam  @NotBlank(message = "参数错误") @Min(1) Long id) {
        Teacher teacher = teacherService.findById(id);
        if(teacher == null) {
            return Response.error("未找到该教师");
        }
        return Response.success(teacher, "获取成功");
    }

    @PostMapping("/save")
    public Response<Teacher> saveTeacher(Teacher teacher) {
        Teacher savedTeacher = teacherService.save(teacher);
        return Response.success(savedTeacher, "操作成功");
    }

    @DeleteMapping("/delete")
    public Response<String> deleteTeacherById(@RequestParam @NotBlank(message = "参数错误") @Min(1) Long id) {
        teacherService.deleteById(id);
        return Response.successM("成功删除教师,id: " + id);
    }

}
