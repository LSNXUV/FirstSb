package com.example.firstsb.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity@Getter@Setter
@Table(name = "teachers")
public class Teacher {
    @Id
    private Long id;         //工号

    //部门
    @Column(nullable = false)
    @NotBlank(message = "部门不能为空")
    @Size(min = 2, max = 20, message = "部门长度必须在2-20之间")
    private String department;   //部门

    @Column(nullable = false)
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20之间")
    private String name;    //姓名

    @Column(nullable = false)
    @Min(value = 16, message = "年龄必须大于15岁")
    @Max(value = 80, message = "年龄必须小于80岁")
    private int age;        //年龄

    @Column(nullable = false)
    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "[男女]", message = "不接受非生物")
    private String gender;  //性别

    @Column(nullable = false,length = 11)
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "1[0-9]{10}", message = "你家电话长这样?")
    private String phone;   //电话

    @Column(nullable = false,length = 50)
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;   //邮箱


}
