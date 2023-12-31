package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindByIdPostService {

    private final PostRepository repository;

    public Post execute(UUID id){
        Optional<Post> post = this.repository.findById(id);

        if(post.isEmpty()){
            throw new PostNotFoundedException("Post not founded");
        }

        return post.get();
    }
}
