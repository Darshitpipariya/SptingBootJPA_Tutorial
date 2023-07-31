package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course dba=
                Course.builder()
                        .title("DBA")
                        .credit(4)
                        .build();
        Course java=
                Course.builder()
                        .title("Java")
                        .credit(5)
                        .build();
        Teacher teacher=
                Teacher.builder()
                        .fName("Darshit")
                        .LName("Pipariya")
//                        .courseList(List.of(dba,java))
                        .build();
        teacherRepository.save(teacher);
    }
}