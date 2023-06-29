package com.galmv_.niceia.studentClass.studentService;

import com.galmv_.niceia.student.StudentDTO;
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
public class TestUpdateStudentService {

    @Resource
    private StudentRepository repository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("it should to able to update a existing user")
    public void testSuccessUpdate(){
        Student student = new Student(null ,"gu", "almeida", "gualmeida@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        repository.save(student);

        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        this.studentService.update(student.getId(), studentDTO);

        Assert.assertEquals("gua@mail.com", this.studentService.findById(student.getId()).getEmail());
    }

    @Test
    @DisplayName("it should not to be able to update a user whether does exists!")
    public void testFailUpdate(){
        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        Assert.assertThrows(UserNotFoundException.class, () -> this.studentService.update(new UUID(0, 0), studentDTO));
    }

}
