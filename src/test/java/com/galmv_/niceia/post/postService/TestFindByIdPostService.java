package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestFindByIdPostService extends UnitTestFactory {

    @Test
    @DisplayName("it should to be able to find a post by id")
    public void testSuccessFindById(){

        Post postToTest = this.postService.findById(post.getId());

        Assert.assertNotNull(postToTest);
    }

    @Test
    @DisplayName("it should not to be able to find a post by id whether don't exists")
    public void testFailFindById(){
        Assert.assertThrows(PostNotFoundedException.class, () -> this.postService.findById(new UUID(0, 0)));
    }
}
