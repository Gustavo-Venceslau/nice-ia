package com.galmv_.niceia.auth.services;

import com.galmv_.niceia.auth.AuthenticationRequest;
import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.UserInvalidCredentialsException;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final StudentRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse execute(AuthenticationRequest request){
        Optional<Student> optionalStudent = repository.findByEmail(request.getEmail());

        if(optionalStudent.isEmpty()){
            throw new UserInvalidCredentialsException("User credentials are invalid");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(optionalStudent.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
