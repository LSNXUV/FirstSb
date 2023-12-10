package com.example.firstsb.controller;


import com.example.firstsb.lib.ResponseData;
import com.example.firstsb.lib.auth.JwtUtil;
import com.example.firstsb.model.User;
import com.example.firstsb.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Data
    public class LoginData {
        @NotNull(message = "用户名不能为空")
        @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")
        public String username;
        @NotNull(message = "密码不能为空")
        @Size(min = 6, max = 32, message = "密码长度必须在6-32之间")
        public String password;
    }

    //登录
    @PostMapping("/login")
    public ResponseEntity<ResponseData<String>> login(@Valid LoginData loginData, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(new ResponseData<>(errorMsg), HttpStatus.OK);
        }
        String jwt = userService.login(loginData.getUsername(), loginData.getPassword());
        if(jwt == null){
            return new ResponseEntity<>(new ResponseData<>("用户名或密码错误"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseData<>(jwt, "登录成功"), HttpStatus.OK);
    }

    //注册
    @PostMapping("/register")
    public ResponseEntity<ResponseData<String>> register(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(new ResponseData<>(errorMsg), HttpStatus.OK);
        }
        String jwt = userService.save(user);
        return new ResponseEntity<>(new ResponseData<>(jwt, "注册成功"), HttpStatus.OK);
    }
}
