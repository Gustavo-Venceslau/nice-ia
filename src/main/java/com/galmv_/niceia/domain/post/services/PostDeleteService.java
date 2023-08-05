package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostDeleteService {

    private final FindByIdPostService findByIdPostService;
    private final ReactionRepository reactionRepository;
    private final CommentRepository commentRepository;
    private final PostRepository repository;

    @Transactional
    public void execute(UUID id) {
        Post postToDelete = findByIdPostService.execute(id);

        if(postToDelete == null){
            throw new PostNotFoundedException("post not founded");
        }

        this.reactionRepository.deleteAllByPost(postToDelete);
        this.commentRepository.deleteAllByPost(postToDelete);
        this.repository.delete(postToDelete);
    }
}
