package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCreateService {

    private final StudentRepository studentRepository;
    private final PostRepository repository;

    public Post execute(PostDTO post){
        Optional<Student> optionalStudent = studentRepository.findById(post.studentId());

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        Post postToSave = Post.builder()
                .title(post.title())
                .imageURL(post.imageURL())
                .student(optionalStudent.get())
                .build();

        return this.repository.save(postToSave);
    }
}
