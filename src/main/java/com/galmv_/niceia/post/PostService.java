package com.galmv_.niceia.post;

import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Post findById(UUID id){
        Optional<Post> post = this.repository.findById(id);

        if(post.isEmpty()){
            throw new PostNotFoundedException("Post not founded");
        }

        return post.get();
    }

    public Post create(Post post){
        return this.repository.save(post);
    }

    public void update(UUID id, PostDTO postNewData) {
        Post postToUpdate = findById(id);

        updatePostData(postToUpdate, postNewData);

        this.repository.save(postToUpdate);
    }

    private void updatePostData(Post postToUpdate, PostDTO postNewData) {
        postToUpdate.setTitle(postNewData.title());
        postToUpdate.setImageURL(postNewData.imageURL());
    }

    public void delete(UUID id) {
        Post postToDelete = findById(id);

        if(postToDelete == null){
            throw new PostNotFoundedException("post not founded");
        }

        this.repository.delete(postToDelete);
    }
}
