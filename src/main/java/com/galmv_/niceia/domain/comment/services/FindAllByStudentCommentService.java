package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
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
public class FindAllByStudentCommentService {

    private final StudentRepository studentRepository;
    private final CommentRepository repository;

    public List<Comment> execute(UUID id){
        Optional<Student> studentExists = studentRepository.findById(id);

        if(studentExists.isEmpty()){
            throw new UserNotFoundException("User not founded, impossible found any comment");
        }

        System.out.println(studentExists.get());

        return repository.findByStudent(studentExists.get());
    }
}
