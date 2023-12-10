package com.example.firstsb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity@Getter@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             //id

    //唯一
    @Column(nullable = false, unique = true)@NotNull
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20之间")
    private String name;        //姓名

    @Column(nullable = false)@NotNull
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")
    private String username;    //用户名

    //密码,md5
    @Column(nullable = false)@NotNull
    @Size(min = 6, max = 32, message = "密码长度必须在6-32之间")
    private String password;    //密码,md5

}
