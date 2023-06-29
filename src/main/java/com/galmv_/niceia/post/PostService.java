package com.galmv_.niceia.post;

import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public List<Post> findAll() {
        List<Post> posts = this.repository.findAll();

        if(posts.isEmpty()){
            throw new PostNotFoundedException("Posts not founded!");
        }

        return posts;
    }
}
