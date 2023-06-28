package com.galmv_.niceia.studentClass.studentService;

import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.StudentService;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.enums.StudentRole;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDelete {

    @Resource
    private StudentRepository repository;

    @Autowired
    private StudentService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("it should to be able to delete a existing student")
    public void testSuccessDelete(){
        Student student = new Student(null ,"gu", "almeida", "gualmeida@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        this.repository.save(student);

        this.service.delete(student.getId());

        Assert.assertTrue(this.repository.findById(student.getId()).isEmpty());
    }

    @Test
    @DisplayName("it should not to be able to delete a student nonexisting student")
    public void testFailDelete(){
        Assert.assertThrows(UserNotFoundException.class,() -> this.service.delete(new UUID(0, 0)));
    }
}
