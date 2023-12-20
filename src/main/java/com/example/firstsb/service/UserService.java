package com.example.firstsb.service;

import com.example.firstsb.lib.auth.JwtUtil;
import com.example.firstsb.model.User;
import com.example.firstsb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //登录返回jwt
    public String login(String username, String password) {
        //密码MD5 32位加密, 与数据库中的密码比较
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        }

        //生成jwt
        return JwtUtil.generateToken(user);
    }

    //add
    public String save(User user) {
        //密码MD5 32位加密, 保存到数据库
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userRepository.save(user);

        //生成返回jwt
        return null;
    }

    public boolean findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
