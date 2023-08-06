package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentCreateService {

    private final PostRepository postRepository;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final CommentRepository repository;

    public Comment execute(String commentText, UUID postId, String token) {
        String jwtTokenFormatted = token.split(" ")[1].trim();

        Optional<Post> optionalCommentPost = this.postRepository.findById(postId);
        String studentEmail = this.jwtService.extractUsername(jwtTokenFormatted);

        Optional<Student> optionalCommentStudent = this.studentRepository.findByEmail(studentEmail);

        if(optionalCommentStudent.isEmpty()){
            throw new EntityNotFoundException("Student not found");
        }

        if(optionalCommentPost.isEmpty()){
            throw new EntityNotFoundException("Post not found");
        }

        if(commentText.isEmpty()){
            throw new EntityNotFoundException("Comment is empty");
        }

        Comment commentToCreate = new Comment(null, commentText, optionalCommentPost.get(), optionalCommentStudent.get());

        return this.repository.save(commentToCreate);
    }
}
