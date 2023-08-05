package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.domain.post.services.FindAllPostService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestFindAllPostService extends UnitTestFactory {

    @Autowired
    private FindAllPostService findAllPostService;

    @Test
    @DisplayName("it should be able to find all posts")
    public void testSuccessFindAll(){
        List<Post> posts = this.findAllPostService.execute();

        Assert.assertFalse(posts.isEmpty());
    }

    @Test
    @DisplayName("it should not to be able find all whether a list is empty")
    public void testFailFindAll(){
        this.reactionRepository.deleteAll();
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();

        Assert.assertThrows(PostNotFoundedException.class, () -> this.findAllPostService.execute());
    }
}
