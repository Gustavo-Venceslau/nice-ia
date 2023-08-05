package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.domain.post.services.PostCreateService;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestCreatePostService extends UnitTestFactory {

    @Autowired
    private PostCreateService postCreateService;

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){

        Post post1 = this.postCreateService.execute(new PostDTO("new post", "image", student.getId()));

        Assert.assertNotNull(post1.getId());
    }

    @Test
    @DisplayName("it should not be able to create a post if student not exists")
    public void testFailCreatePost(){

        Assert.assertThrows(UserNotFoundException.class, () ->
                this.postCreateService.execute(new PostDTO("new post", "image", new UUID(0, 0))));
    }
}
