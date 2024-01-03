package com.example.firstsb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity@Getter@Setter
@Table(name = "courses")
public class Course {
    @Id@NotNull(message = "课程号不能为空")
    private Long id;         //课程号

    @Column(unique = true,nullable = false)
    @NotBlank(message = "课程名不能为空")
    @Size(min = 2, max = 20, message = "课程名长度必须在2-20之间")
    private String name;     //课程名

    @Column(nullable = false)
    @Min(value = 0, message = "学分必须大于0")
    private double credit;     //学分

    @Column(nullable = false)
    @Min(value = 1, message = "学时必须大于0")
    private int hours;      //学时
}
