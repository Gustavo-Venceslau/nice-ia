package com.galmv_.niceia.studentClass.studentService;

import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.exceptions.UserAlreadyExistsException;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRegisterStudentService {

    @Resource
    private StudentRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("it should to be able create a student")
    public void testSuccessRegisterStudent(){
        RegisterRequest request = new RegisterRequest("gu", "almeida", "gualmeida@mail.com", "123456");

        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        AuthenticationResponse responseToken = this.authenticationService.register(request);
        Assert.assertTrue(jwtService.isTokenValid(responseToken.getToken(), student));
    }

    @Test
    @DisplayName("it should not be able to create")
    public void testFailRegisterStudent(){
        RegisterRequest request2 = new RegisterRequest("gu", "almeida", "gualmeida@mail.com", "123456");

        Assert.assertThrows(UserAlreadyExistsException.class, () -> this.authenticationService.register(request2));
    }
}
