package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {

    private final FindByIdCommentService findByIdCommentService;
    private final CommentRepository repository;

    public void execute(UUID id) {
        Comment comment = findByIdCommentService.execute(id);

        this.repository.delete(comment);
    }
}
