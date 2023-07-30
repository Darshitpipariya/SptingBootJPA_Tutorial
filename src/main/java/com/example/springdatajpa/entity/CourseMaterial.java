package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "course_material"
)
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name="course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne(
            // it will do all cascading operation so for example for inserting course material we will have to add course in course table using cascading save it first store course and then it will store course material
            cascade = CascadeType.ALL,
            // LAZY= load course only if it required no always    , EAGER =load course always        these properties it will be helpfull when we will have circular dependency
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            // join column name will be course_id
            name = "course_id",
            // it is referencing to courseId in Course table
            referencedColumnName = "courseId"
    )
    private Course course;
}
