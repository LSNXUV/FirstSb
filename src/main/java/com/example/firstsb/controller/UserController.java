package com.example.firstsb.controller;


import com.example.firstsb.lib.Response;
import com.example.firstsb.model.User;
import com.example.firstsb.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@CrossOrigin
@RestController
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
    public Response<String> login(@Valid LoginData loginData, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.error(errorMsg);
        }
        String jwt = userService.login(loginData.getUsername(), loginData.getPassword());
        if(jwt == null){
            return Response.error("用户名或密码错误");
        }
        return Response.success(jwt,"登录成功");
    }

    //注册
    @PostMapping("/register")
    public Response<String> register(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            // 处理验证错误
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.error(errorMsg);
        }
        //看用户名是否已经存在
        if(userService.findByUsername(user.getUsername())){
            return Response.error("用户名已存在");
        }
        String jwt = userService.save(user);
        return Response.success(jwt,"注册成功");
    }
}
