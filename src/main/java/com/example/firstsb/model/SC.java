package com.example.firstsb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity@Setter@Getter
@Table(name = "sc",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cid", "sid"})
})
public class SC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 主键

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Course course;   // 课程号，外键

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sid", referencedColumnName = "id")
    private Student student; // 学号，外键

    //score,默认值为null
    @Column(nullable = true)
    @Min(value = 0, message = "分数必须大于等于0")
    @Max(value = 100, message = "分数必须小于等于100")
    private int score;
}
