package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.UnitTestFactory;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostDTO;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitTestCreatePostService extends UnitTestFactory {

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){

        String token = jwtService.generateToken(student);

        Post post1 = this.postService.create(new PostDTO("new post", "image"), token);

        Assert.assertNotNull(post1.getId());
    }
}
