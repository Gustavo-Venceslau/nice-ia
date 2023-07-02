package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class TestFindAllPostService extends TestFactory {

    @Test
    @DisplayName("it should be able to find all posts")
    public void testSuccessFindAll(){
        List<Post> posts = this.postService.findAll();

        Assert.assertFalse(posts.isEmpty());
    }

    @Test
    @DisplayName("it should not to be able find all whether a list is empty")
    public void testFailFindAll(){
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();

        Assert.assertThrows(PostNotFoundedException.class, () -> this.postService.findAll());
    }
}
