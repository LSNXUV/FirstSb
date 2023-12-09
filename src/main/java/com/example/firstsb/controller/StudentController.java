package com.example.firstsb.controller;

import com.example.firstsb.model.Student;
import com.example.firstsb.service.StudentService;
import com.example.firstsb.lib.ResponseData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5500") // 允许来自特定来源的跨域请求
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //查询所有学生
    @GetMapping(value = "all")
    public ResponseEntity<ResponseData<Page<Student>>> getAllStudents(
            @RequestParam(required = false) @Min(0) Integer page,
            @RequestParam(required = false) @Min(1) Integer size) {

        if (size != null && size <= 0) {
            return new ResponseEntity<>(new ResponseData<>("Size必须大于0"), HttpStatus.BAD_REQUEST);
        }
        if(page != null && page <= 0) {
            return new ResponseEntity<>(new ResponseData<>("Page必须大于0"), HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = (page != null && size != null) ? PageRequest.of(--page, size) : null;
        Page<Student> students = studentService.findAll(pageable);
        ResponseData<Page<Student>> responseData = new ResponseData<>(students);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // 添加或更新学生
    @PostMapping("/save")
    public ResponseEntity<ResponseData<Student>> addOrUpdateStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(new ResponseData<>(-1, errorMsg), HttpStatus.BAD_REQUEST);
        }
        Student savedStudent = studentService.save(student);
        ResponseData<Student> responseData = new ResponseData<>(savedStudent);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    // 删除学生
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData<String>> deleteStudent(@RequestParam @Min(1) Long id) {
        studentService.deleteById(id);
        ResponseData<String> responseData = new ResponseData<>("成功删除学生, id: " + id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //  根据姓名查询返回 JSON 数据
    @GetMapping("/searchByName")
    public ResponseEntity<ResponseData<List<Student>>> getStudentByName(@RequestParam @NotBlank(message = "Name cannot be blank") String name) {
        List<Student> students = studentService.findByName(name);
        ResponseData<List<Student>> responseData = new ResponseData<>(students);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // 根据 ID 查询返回 JSON 数据
    @GetMapping("/searchById")
    public ResponseEntity<ResponseData<Student>> getStudentById(@RequestParam @NotBlank(message = "参数错误") @Min(1) Long id) {
        Student student = studentService.findById(id);
        ResponseData<Student> responseData = new ResponseData<>(student);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
