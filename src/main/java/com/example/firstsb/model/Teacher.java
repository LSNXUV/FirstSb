package com.example.firstsb.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id@Getter
    private Long id;         //工号
    @Column(nullable = false)@Getter
    private String name;
    //age
    @Column(nullable = false)@Getter
    private int age;
    //gender
    @Column(nullable = false)@Getter
    private String gender;



}
