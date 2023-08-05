package com.galmv_.niceia.authentication;

import com.galmv_.niceia.auth.AuthenticationRequest;
import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.UserInvalidCredentialsException;
import com.galmv_.niceia.auth.services.AuthenticateService;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAuthenticateService extends UnitTestFactory {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticateService authenticateService;

    @Test
    @DisplayName("should to be able to authenticate a user if this one exists")
    public void testSuccessAuthenticate(){

        AuthenticationRequest request = new AuthenticationRequest(studentAuthenticate.getEmail(), studentAuthenticate.getPassword());

        AuthenticationResponse response = authenticateService.execute(request);

        Assert.assertNotNull(response.getToken());
    }

    @Test
    @DisplayName("should not to be able to authenticate a user if this one don't exists")
    public void testFailAuthenticate(){
        AuthenticationRequest request = new AuthenticationRequest("johndoe@mail.com", "000");

        Assert.assertThrows(UserInvalidCredentialsException.class, () -> this.authenticateService.execute(request));
    }
}
