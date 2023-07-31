package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "course"
)
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            // as it is already mapped in course_material class, so we will say it is mapped by course attribute
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

//    whenever possible use ManyToOne relationship instead of OneToMany  it is more readable when we have unidirectional relationships.

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // in manytomay relationship we have to create new table so we will use joincolumn annotation
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                            name = "course_id",
                            referencedColumnName = "courseId"
                    ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id"
            )
    )
    private List<Student> studentList;


    // when working with list we can create add method to class
    public void addStudent(Student student){
        if(studentList==null) studentList=new ArrayList<>();
        studentList.add(student);
    }
}
