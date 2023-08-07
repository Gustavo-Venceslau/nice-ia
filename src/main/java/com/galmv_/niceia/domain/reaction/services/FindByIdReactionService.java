package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindByIdReactionService {

    private final ReactionRepository repository;

    public Reaction execute(UUID id) {
        Optional<Reaction> optionalReaction = this.repository.findById(id);

        if(optionalReaction.isEmpty()){
            throw new ReactionNotFoundedException("Reaction not founded");
        }

        return optionalReaction.get();
    }
}
