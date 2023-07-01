package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.TestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestCreatePostService extends TestFactory {

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){
        this.studentRepository.save(student);
        this.postService.create(post);

        Assert.assertNotNull(post.getId());
    }
}
