package com.example.firstsb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: firstsb
 * @description: Student实体类，对应数据库中的students表
 * @version: 1.0.0
 */

@Entity
@Getter@Setter
@Table(name = "students")
public class Student {
    @Id @NotNull(message = "学号不能为空")
    private Long id;         //学号

    @Column(nullable = false) @NotBlank(message = "专业不能为空")
    @Size(min = 2, max = 20, message = "专业长度必须在2-20之间")
    private String major;   //专业

    @Column(nullable = false) @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20之间")
    private String name;     //姓名

    @Column(nullable = false)
    @Min(value = 16, message = "年龄必须大于15岁") @Max(value = 40, message = "年龄必须小于41岁")
    private int age;        //年龄

    @Column(nullable = false) @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "[男女]", message = "不接受非生物")
    private String gender;  //性别

    @Column(nullable = false,length = 11) @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "1[0-9]{10}", message = "你家电话长这样?")
    private String phone;   //电话

    @Column(nullable = false,length = 50) @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;   //邮箱
}
