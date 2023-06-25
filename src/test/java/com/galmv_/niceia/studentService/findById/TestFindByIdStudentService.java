package com.galmv_.niceia.studentService.findById;

import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.StudentService;
import com.galmv_.niceia.student.entities.Student;
import com.galmv_.niceia.student.enums.StudentRole;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFindByIdStudentService {

    @Resource
    private StudentRepository repository;

    @Autowired
    private StudentService service;

    @Test
    @DisplayName("it should able to find a user by id if the user exists!")
    public void testSuccessFindById(){
        Student studentToGetId = repository
                .save(new Student(null, "meida", "vence", "meidavence@mail.com", "321", StudentRole.USER));

        Student student = this.service.findById(studentToGetId.getId());
        Assert.assertNotNull(student.getId());
    }

    @Test
    @DisplayName("it should not to be able to find a user by id if does not exist")
    public void testFailFindById(){
        Assert.assertThrows(UserNotFoundException.class, () -> this.service.findById(new UUID(0, 0)));
    }

}
