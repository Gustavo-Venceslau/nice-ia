package com.galmv_.niceia.domain.student.services;

import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentDeleteService {

    private final StudentRepository repository;
    private final ReactionRepository reactionRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final FindByIdStudentService findByIdStudentService;

    @Transactional
    public void execute(UUID id){
        Student student = findByIdStudentService.execute(id);

        if(student == null){
            throw new UserNotFoundException("User not founded");
        }

        this.reactionRepository.deleteAllByStudent(student);
        this.commentRepository.deleteAllByStudent(student);
        this.postRepository.deleteAllByStudent(student);
        this.repository.delete(student);
    }
}
