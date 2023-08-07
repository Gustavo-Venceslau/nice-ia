package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactionUpdateService {

    private final FindByIdReactionService findByIdReactionService;
    private final ReactionRepository repository;

    public void execute(UUID id, Type type) {
        Reaction reaction = findByIdReactionService.execute(id);

        reaction.setType(type);

        this.repository.save(reaction);
    }
}
