package com.example.firstsb.controller;


import com.example.firstsb.lib.ResponseData;
import com.example.firstsb.model.User;
import com.example.firstsb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //登录
    @PostMapping("/login")
    public ResponseData<ResponseEntity<String>> login(@RequestBody User user) {
        String jwt = userService.login(user.getUsername(), user.getPassword());
        return new ResponseData<>(ResponseEntity.ok(jwt));
    }

    //注册
    @PostMapping("/register")
    public ResponseData<ResponseEntity<String>> register(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseData<>(ResponseEntity.badRequest().body(errorMsg));
        }
        String jwt = userService.save(user);
        return new ResponseData<>(ResponseEntity.ok(jwt), "注册成功");
    }
}
