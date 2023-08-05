package com.galmv_.niceia.auth.controllers;

import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.auth.services.RegisterService;
import com.galmv_.niceia.domain.student.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> handle(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(registerService.execute(request));
        }
        catch (UserAlreadyExistsException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.badRequest().build();
        }
    }
}
