package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllPostService {

    private final PostRepository repository;

    public List<Post> execute() {
        List<Post> posts = this.repository.findAll();

        if(posts.isEmpty()){
            throw new PostNotFoundedException("Posts not founded!");
        }

        return posts;
    }
}
