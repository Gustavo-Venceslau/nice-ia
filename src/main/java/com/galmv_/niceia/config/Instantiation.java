package com.galmv_.niceia.config;

import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.auth.services.RegisterService;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final PostRepository postRepository;

    private final StudentRepository studentRepository;

    private final CommentRepository commentRepository;

    private final ReactionRepository reactionRepository;

    private final RegisterService registerService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest student1 = new RegisterRequest("Gustavo", "de Almeida", "gustavo@mail.com", "123");
        RegisterRequest student2 = new RegisterRequest("sandro", "paz", "sandro@mail.com", "12345");
        RegisterRequest student3 = new RegisterRequest("maria", "souza", "marias@mail.com", "123345");

        registerService.execute(student1);
        registerService.execute(student2);
        registerService.execute(student3);

        Student student4 = new
                Student(null, "Almeida", "Venceslau", "almeida@mail.com", passwordEncoder.encode("12345"), StudentRole.USER);
        Student student5 = new
                Student(null, "Venceslau", "Almeida", "venceslau@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        this.studentRepository.saveAll(List.of(student4, student5));

        Post post1 = new Post(null, "Good day", "", student4);
        Post post2 = new Post(null, "Good Evening", "", student5);
        Post post3 = new Post(null, "Good Afternoon", "", student4);

        this.postRepository.saveAll(List.of(post1, post2, post3));

        Comment comment1 = new Comment(null, "Good way, guys!", post1, student4);
        Comment comment2 = new Comment(null, "guys!", post1, student5);

        this.commentRepository.saveAll(List.of(comment1, comment2));

        Reaction reaction1 = new Reaction(null, Type.LIKE, post1, comment1, student4);
        Reaction reaction2 = new Reaction(null, Type.LOVED, post1, comment1, student5);

        this.reactionRepository.saveAll(List.of(reaction1, reaction2));
    }
}
