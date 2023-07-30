package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);

// implement custome JPQL query where column name will be same as attribute name in class  JPQL are base on the class we created
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Student fetchByEmail(String email);

    // for native query we have to use original column name as in sql table
    @Query(
            value = "SELECT * FROM student_table s WHERE s.guardian_name=?1",
            nativeQuery = true
    )
    Student fetchByGuardianName(String guardianName);

//   Native named parameter
    @Query(
            value = "SELECT * FROM student_table s WHERE s.guardian_name=:guardianName",
            nativeQuery = true
    )
    Student fetchByGuardianNameNamedParameter(@Param("guardianName") String guardianName);


    // native update query as we are changing data in database we will have to use transaction ideally it is used at service layer where we may have to modify multiple table so at it if any error occur then we can roll back the operation
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE STUDENT_TABLE SET name=?1 WHERE email_id=?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String name,String email);
}
