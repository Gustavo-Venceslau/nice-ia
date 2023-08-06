package com.galmv_.niceia.domain.comment.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentUpdateService {

    private final FindByIdCommentService findByIdCommentService;
    private final CommentRepository repository;

    public void execute(UUID id, CreateAndUpdateCommentDTO newData) {
        Comment commentToUpdate = findByIdCommentService.execute(id);

        commentToUpdate.setText(newData.text());

        this.repository.save(commentToUpdate);
    }
}
