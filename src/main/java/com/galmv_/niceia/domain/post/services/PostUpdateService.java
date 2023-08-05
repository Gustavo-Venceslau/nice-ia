package com.galmv_.niceia.domain.post.services;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostUpdateService {

    private final FindByIdPostService findByIdPostService;
    private final PostRepository repository;

    public void execute(UUID id, PostDTO postNewData) {
        Post postToUpdate = findByIdPostService.execute(id);

        updatePostData(postToUpdate, postNewData);

        this.repository.save(postToUpdate);
    }

    private void updatePostData(Post postToUpdate, PostDTO postNewData) {
        postToUpdate.setTitle(postNewData.title());
        postToUpdate.setImageURL(postNewData.imageURL());
    }
}
