package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.services.FindByIdCommentService;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByCommentReactionService {

    private final FindByIdCommentService findByIdCommentService;
    private final ReactionRepository repository;

    public List<Reaction> execute(UUID id) {
        Comment commentToFind = this.findByIdCommentService.execute(id);

        return this.repository.findAllByComment(commentToFind);
    }
}
