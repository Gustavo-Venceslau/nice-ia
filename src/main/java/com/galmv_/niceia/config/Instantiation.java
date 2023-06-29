package com.galmv_.niceia.config;

import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.enums.StudentRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final PostRepository postRepository;

    private final StudentRepository studentRepository;

    private final AuthenticationService service;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest student1 = new RegisterRequest("Gustavo", "de Almeida", "gustavo@mail.com", "123");
        RegisterRequest student2 = new RegisterRequest("sandro", "paz", "sandro@mail.com", "12345");
        RegisterRequest student3 = new RegisterRequest("maria", "souza", "marias@mail.com", "123345");

        service.register(student1);
        service.register(student2);
        service.register(student3);

        Student student4 = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);
        Student student5 = new Student(null, "Venceslau", "Almeida", "venceslau@mail.com", "123456", StudentRole.USER);

        this.studentRepository.saveAll(List.of(student4, student5));

        Post post1 = new Post(null, "Good day", "", student4);
        Post post2 = new Post(null, "Good Evening", "", student5);
        Post post3 = new Post(null, "Good Afternoon", "", student4);

        this.postRepository.saveAll(List.of(post1, post2, post3));


    }
}
