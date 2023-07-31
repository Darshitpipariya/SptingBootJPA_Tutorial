package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    // pagging method from JpaRepository usage
    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords=PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords=PageRequest.of(1,2);

        List<Course> firstCourseList=courseRepository.findAll(firstPageWithThreeRecords).getContent();
        List<Course> secondCourseList=courseRepository.findAll(secondPageWithTwoRecords).getContent();
        System.out.println("First Course List "+firstCourseList);
        System.out.println("First Pagging Total pages "+courseRepository.findAll(firstPageWithThreeRecords).getTotalPages()+" with size "+courseRepository.findAll(firstPageWithThreeRecords).getTotalElements());
        System.out.println("Second Course List "+secondCourseList);
        System.out.println("Second Pagging Total pages "+courseRepository.findAll(secondPageWithTwoRecords).getTotalPages()+" with size "+courseRepository.findAll(secondPageWithTwoRecords).getTotalElements());
    }

    @Test
    public void findAllPaginationWithSorting(){
        Pageable sortByTitle=PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDecending=PageRequest.of(0,2, Sort.by("credit").descending());
        List<Course> sortCourseListByTitle=courseRepository.findAll(sortByTitle).getContent();
        System.out.println("sorted Course List By title "+sortCourseListByTitle);
        List<Course> sortCourseListByCreditDecending=courseRepository.findAll(sortByCreditDecending).getContent();
        System.out.println("sorted Course List By title "+sortCourseListByCreditDecending);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageWithTwoRecords=PageRequest.of(0,2);
        List<Course> courseList=courseRepository.findByTitleContaining("D",firstPageWithTwoRecords).getContent();
        System.out.println("printFindByTitleContaining D "+courseList);
    }
}