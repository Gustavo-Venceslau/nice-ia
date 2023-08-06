package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindByIdCommentService {

    private final CommentRepository repository;

    public Comment execute(UUID id) {
        Optional<Comment> optionalComment = this.repository.findById(id);

        if(optionalComment.isEmpty()){
            throw new CommentNotFoundException("Comment not founded!");
        }

        return optionalComment.get();
    }
}
