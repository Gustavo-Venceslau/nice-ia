package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByStudentPostService {

    private final StudentRepository studentRepository;
    private final PostRepository repository;

    public List<Post> execute(UUID id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        return repository.findAllByStudent(optionalStudent.get());
    }
}
