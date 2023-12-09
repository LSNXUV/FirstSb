package com.example.firstsb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity@Setter@Getter
@Table(name = "tc")
public class TC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 主键

    @ManyToOne
    @JoinColumn(name = "tid", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)  // 删除教师时，删除选课信息
    private Teacher teacher; // 教师号，外键

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)  // 删除课程时，删除选课信息
    private Course course;   // 课程号，外键
}
