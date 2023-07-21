package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCreatePostService extends UnitTestFactory {

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){

        String token = "Bearer " + jwtService.generateToken(student);

        Post post1 = this.postService.create(new PostDTO("new post", "image"), token);

        Assert.assertNotNull(post1.getId());
    }
}
