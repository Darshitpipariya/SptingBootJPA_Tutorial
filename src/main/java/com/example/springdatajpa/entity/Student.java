package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "student_table",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_id"
        )
)
public class Student {
    @Id
    @SequenceGenerator(name = "stud_sequence",sequenceName = "stud_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stud_sequence")
    private Long id;

    private String name;
    @Column(
            name = "email_id",
            nullable = false
    )
    private String email;

    private String mobile;
    @Embedded
    private Guardian guardian;


}
