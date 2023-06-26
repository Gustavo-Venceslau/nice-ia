package com.galmv_.niceia.studentService;

import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.StudentService;
import com.galmv_.niceia.student.entities.Student;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFindAll {

    @Resource
    private StudentRepository repository;

    @Autowired
    private StudentService service;

    @Test
    @DisplayName("Test if find all methods retrieve all students")
    public void testSuccessFindAllStudents(){
        Set<Student> studentsList = this.service.findAll();
        Assert.assertFalse(studentsList.isEmpty());
    }

    @Test
    @DisplayName("it should not possible to findAll if the students does not exist")
    public void testFailFindAllStudents(){
        repository.deleteAll();
        Assert.assertThrows(UserNotFoundException.class, () -> this.service.findAll());
    }

}
