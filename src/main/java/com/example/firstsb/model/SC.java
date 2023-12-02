package com.example.firstsb.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "sc")
public class SC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 主键

    @ManyToOne@Setter
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Course course;   // 课程号，外键

    @ManyToOne@Setter
    @JoinColumn(name = "sid", referencedColumnName = "id")
    private Student student; // 学号，外键
}
