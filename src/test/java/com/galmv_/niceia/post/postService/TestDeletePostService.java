package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestDeletePostService extends TestFactory {
    @Test
    @DisplayName("should to be able to delete a post if it exist")
    public void testSuccessDeletePost(){
        this.studentRepository.save(student);
        this.postService.create(post);

        this.postService.delete(post.getId());

        Assert.assertTrue(postRepository.findById(post.getId()).isEmpty());
    }

    @Test
    @DisplayName("should not to be able to delete a post if it don't exists")
    public void testFailDeletePost(){
        postRepository.deleteAll();
        Assert.assertThrows(PostNotFoundedException.class, () -> this.postService.delete(new UUID(0, 0)));
    }
}
