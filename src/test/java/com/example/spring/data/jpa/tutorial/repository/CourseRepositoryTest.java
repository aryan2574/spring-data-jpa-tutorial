package com.example.spring.data.jpa.tutorial.repository;

import com.example.spring.data.jpa.tutorial.entity.Course;
import com.example.spring.data.jpa.tutorial.entity.Student;
import com.example.spring.data.jpa.tutorial.entity.Teacher;
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

    @Test
    public void printCourses() {
        List<Course> courseList = courseRepository.findAll();

        System.out.println("Courses = " + courseList);
    }

    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("Courses = " + courses);

        System.out.println("Total page = " + totalElements);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("Courses = " + courses);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByTitleContaining(
                "D", firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}
