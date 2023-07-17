package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.domain.comment.exceptions.CommentIsEmptyException;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
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
public class CommentService {

    private final CommentRepository commentRepository;

    private final StudentRepository studentRepository;

    public List<Comment> findAllByStudent(String email){
        Optional<Student> studentExists = studentRepository.findByEmail(email);

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

    public Comment create(CommentDTO commentData) {
        if(commentData.text().equals("")){
            throw new CommentIsEmptyException("Comment is empty");
        }

        Comment commentToCreate = new Comment(null, commentData.text(), commentData.post(), commentData.student());

        return this.commentRepository.save(commentToCreate);
    }

    public void update(UUID id, String newComment) {
        Comment commentToUpdate = findById(id);

        commentToUpdate.setText(newComment);

        this.commentRepository.save(commentToUpdate);
    }

    public void delete(UUID id) {
        Comment comment = findById(id);

        this.commentRepository.delete(comment);
    }
}
