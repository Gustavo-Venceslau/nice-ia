package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.services.FindByIdPostService;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByPostReactionService {

    private final FindByIdPostService findByIdPostService;
    private final ReactionRepository repository;

    public List<Reaction> execute(UUID id){
        Post post = this.findByIdPostService.execute(id);

        return this.repository.findAllByPost(post);
    }
}
