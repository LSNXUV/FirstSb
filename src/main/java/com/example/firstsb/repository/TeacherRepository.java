package com.example.firstsb.repository;

import com.example.firstsb.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value="select * from teachers where left(id, 4) like left((select max(id) from teachers), 4)", nativeQuery = true)
    List<Teacher> findLatestTeachers();
}
