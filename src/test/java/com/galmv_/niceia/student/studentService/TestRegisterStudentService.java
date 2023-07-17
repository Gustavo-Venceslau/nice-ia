package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.UnitTestFactory;
import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.exceptions.UserAlreadyExistsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRegisterStudentService extends UnitTestFactory {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("it should to be able create a student")
    public void testSuccessRegisterStudent(){
        RegisterRequest request = new RegisterRequest("vence", "almeid", "gueida@mail.com", "12345");

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
        this.authenticationService.register(request2);

        Assert.assertThrows(UserAlreadyExistsException.class, () -> this.authenticationService.register(request2));
    }
}
