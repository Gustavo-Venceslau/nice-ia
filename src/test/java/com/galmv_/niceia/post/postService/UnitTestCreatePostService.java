package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.UnitTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class UnitTestCreatePostService extends UnitTestFactory {

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){
        this.studentRepository.save(student);
        this.postService.create(post);

        Assert.assertNotNull(post.getId());
    }
}
