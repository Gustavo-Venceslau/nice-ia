package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactionDeleteService {

    private final FindByIdReactionService findByIdReactionService;
    private final ReactionRepository repository;

    public void execute(UUID id) {
        Reaction reaction = findByIdReactionService.execute(id);

        this.repository.delete(reaction);
    }
}
