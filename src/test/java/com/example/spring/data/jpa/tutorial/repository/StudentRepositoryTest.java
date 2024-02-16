package com.example.spring.data.jpa.tutorial.repository;

// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.spring.data.jpa.tutorial.entity.Guardian;
import com.example.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// @DataJpaTest
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("test@gmail.com")
                .firstName("test")
                .lastName("testLast")
//                .guardianName("Nikhil")
//                .guardianEmail("nikhil@gmail.com")
//                .guardianMobile("99999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("999994444")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .emailId("shivam@gmail.com")
                .lastName("kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();

        System.out.println("Student List = " + studentList);
    }


    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("shivam");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("sh");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Nikhil");

        System.out.println("students = " + students);

        for (Student student : students) {
            System.out.println("Student Email - " + student.getEmailId());
        }
    }

    @Test
    public void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("test@gmail.com");

        System.out.println("Student = " + student);
    }

    @Test
    public void printStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative(
                "test@gmail.com"
        );

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddressNativeParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam(
                "test@gmail.com"
        );

        System.out.println("student = " + student);
    }
}
