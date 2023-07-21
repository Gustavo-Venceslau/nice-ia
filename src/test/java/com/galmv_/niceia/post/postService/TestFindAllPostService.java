package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class TestFindAllPostService extends UnitTestFactory {

    @Test
    @DisplayName("it should be able to find all posts")
    public void testSuccessFindAll(){
        List<Post> posts = this.postService.findAll();

        Assert.assertFalse(posts.isEmpty());
    }

    @Test
    @DisplayName("it should not to be able find all whether a list is empty")
    public void testFailFindAll(){
        this.reactionRepository.deleteAll();
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();

        Assert.assertThrows(PostNotFoundedException.class, () -> this.postService.findAll());
    }
}
