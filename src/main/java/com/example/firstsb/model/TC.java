package com.example.firstsb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity@Setter@Getter
@Table(name = "tc")
public class TC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 主键

    @ManyToOne
    @JoinColumn(name = "tid", referencedColumnName = "id")
    private Teacher teacher; // 教师号，外键

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Course course;   // 课程号，外键
}
