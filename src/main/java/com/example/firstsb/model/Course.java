package com.example.firstsb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
public class Course {
    @Id@Getter@Setter
    private Long id;         //课程号
    @Column(unique = true,nullable = false)@Getter
    private String name;     //课程名
    @Column(nullable = false)@Getter
    private int credit;     //学分
    @Column(nullable = false)@Getter
    private int hours;      //学时
}
