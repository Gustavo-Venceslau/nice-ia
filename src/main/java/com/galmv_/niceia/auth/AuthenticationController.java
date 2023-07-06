package com.galmv_.niceia.auth;

import com.galmv_.niceia.student.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(service.register(request));
        }
        catch (UserAlreadyExistsException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        try {
            return ResponseEntity.ok(service.authenticate(request));
        }
        catch (BadCredentialsException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
