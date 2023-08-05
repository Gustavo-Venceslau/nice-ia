package com.galmv_.niceia.authentication;

import com.galmv_.niceia.auth.services.RegisterService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.exceptions.UserAlreadyExistsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRegisterService extends UnitTestFactory {

    @Autowired
    private RegisterService registerService;

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

        AuthenticationResponse responseToken = this.registerService.execute(request);
        Assert.assertTrue(jwtService.isTokenValid(responseToken.getToken(), student));
    }

    @Test
    @DisplayName("it should not be able to create")
    public void testFailRegisterStudent(){
        RegisterRequest request2 = new RegisterRequest("gu", "almeida", "gualmeida@mail.com", "123456");
        this.registerService.execute(request2);

        Assert.assertThrows(UserAlreadyExistsException.class, () -> this.registerService.execute(request2));
    }
}
