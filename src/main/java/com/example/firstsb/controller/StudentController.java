package com.example.firstsb.controller;

import com.example.firstsb.model.Student;
import com.example.firstsb.service.StudentService;
import com.example.firstsb.lib.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin// 允许跨域
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //查询所有学生
    @GetMapping(value = "all")
    public Response<Page<Student>> getAllStudents(
            @RequestParam(required = false) @Min(0) Integer page,
            @RequestParam(required = false) @Min(1) Integer size) {

        if (size != null && size <= 0) {
            return Response.error("Size必须大于0");
        }
        if(page != null && page <= 0) {
            return Response.error("Page必须大于0");
        }
        Pageable pageable = (page != null && size != null) ? PageRequest.of(--page, size) : null;
        Page<Student> students = studentService.findAll(pageable);
        return Response.success(students);
    }

    // 添加或更新学生
    @PostMapping("/save")
    public Response<Student> addOrUpdateStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.error(errorMsg);
        }
        Student savedStudent = studentService.save(student);
        return Response.success(savedStudent,"操作成功！");
    }

    // 删除学生
    @DeleteMapping("/delete")
    public Response<String> deleteStudent(@RequestParam @Min(1) Long id) {
        studentService.deleteById(id);
        return Response.successM("成功删除学生,id: " + id);
    }

    //  根据姓名查询返回 JSON 数据
    @GetMapping("/searchByName")
    public Response<List<Student>> getStudentByName(@RequestParam @NotBlank(message = "Name cannot be blank") String name) {
        List<Student> students = studentService.findByName(name);
        return Response.success(students);
    }

    // 根据 ID 查询返回 JSON 数据
    @GetMapping("/searchById")
    public Response<Student> getStudentById(@RequestParam @NotBlank(message = "参数错误") @Min(1) Long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return Response.error("没有找到学生");
        }
        return Response.success(student);
    }
}
