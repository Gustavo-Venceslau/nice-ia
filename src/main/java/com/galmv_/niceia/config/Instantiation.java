package com.galmv_.niceia.config;

import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final StudentRepository repository;

    private final AuthenticationService service;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest student1 = new RegisterRequest("Gustavo", "de Almeida", "gustavo@mail.com", "123");
        RegisterRequest student2 = new RegisterRequest("sandro", "paz", "sandro@mail.com", "12345");
        RegisterRequest student3 = new RegisterRequest("maria", "souza", "marias@mail.com", "123345");

        service.register(student1);
        service.register(student2);
        service.register(student3);

    }
}
