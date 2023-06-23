package com.galmv_.niceia.config;

import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.entities.Student;
import com.galmv_.niceia.student.enums.StudentRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Student student1 = new Student(null, "Gustavo", "de Almeida", "gustavo@mail.com", "123", StudentRole.ADMIN);
        Student student2 = new Student(null, "sandro", "paz", "sandro@mail.com", "12345", StudentRole.USER);
        Student student3 = new Student(null, "maria", "souza", "marias@mail.com", "123345", StudentRole.USER);

        repository.saveAll(List.of(student1, student2, student3));

    }
}
