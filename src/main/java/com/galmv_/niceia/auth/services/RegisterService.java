package com.galmv_.niceia.auth.services;

import com.galmv_.niceia.auth.AuthenticationResponse;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import com.galmv_.niceia.domain.student.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse execute(RegisterRequest request) {
        Optional<Student> optionalStudent = this.repository.findByEmail(request.getEmail());

        if(optionalStudent.isPresent()){
            throw new UserAlreadyExistsException("You can't create because this user already exists!");
        }

        var user = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .studentRole(StudentRole.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
