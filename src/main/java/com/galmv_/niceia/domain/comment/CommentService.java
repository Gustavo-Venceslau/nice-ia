package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final StudentRepository studentRepository;

    private final PostRepository postRepository;

    private final JwtService jwtService;

    public List<Comment> findAllByStudent(UUID id){
        Optional<Student> studentExists = studentRepository.findById(id);

        if(studentExists.isEmpty()){
            throw new UserNotFoundException("User not founded, impossible found any comment");
        }

        System.out.println(studentExists.get());

        return commentRepository.findByStudent(studentExists.get());
    }

    public Comment findById(UUID id) {
        Optional<Comment> optionalComment = this.commentRepository.findById(id);

        if(optionalComment.isEmpty()){
            throw new CommentNotFoundException("Comment not founded!");
        }

        return optionalComment.get();
    }

    public Comment create(String commentText, UUID postId, String token) {
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

        if(commentText.equals("")){
            throw new EntityNotFoundException("Comment is empty");
        }

        Comment commentToCreate = new Comment(null, commentText, optionalCommentPost.get(), optionalCommentStudent.get());

        return this.commentRepository.save(commentToCreate);
    }

    public void update(UUID id, CreateAndUpdateCommentDTO newData) {
        Comment commentToUpdate = findById(id);

        commentToUpdate.setText(newData.text());

        this.commentRepository.save(commentToUpdate);
    }

    public void delete(UUID id) {
        Comment comment = findById(id);

        this.commentRepository.delete(comment);
    }
}
