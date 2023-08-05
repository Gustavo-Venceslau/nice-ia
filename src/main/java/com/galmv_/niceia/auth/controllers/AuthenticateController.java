package com.galmv_.niceia.auth.controllers;

import com.galmv_.niceia.auth.AuthenticationRequest;
import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> handle(
            @RequestBody AuthenticationRequest request
    ){
        try {
            return ResponseEntity.ok(authenticateService.execute(request));
        }
        catch (BadCredentialsException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
