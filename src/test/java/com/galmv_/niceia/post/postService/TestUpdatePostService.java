package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.post.PostDTO;
import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestUpdatePostService extends TestFactory {

    @Test
    @DisplayName("it should to be able to update a post if exist")
    public void testSuccessUpdatePost(){

        PostDTO postDataToUpdate = new PostDTO("title", "image2");

        this.postService.update(post.getId(), postDataToUpdate);

        Assert.assertNotEquals("Good day", postService.findById(post.getId()).getTitle());
    }

    @Test
    @DisplayName("it should not to be able to update a post that not exists")
    public void testFailUpdatePost(){
        Assert.assertThrows(PostNotFoundedException.class, ()
                -> this.postService.update(new UUID(0, 0), new PostDTO("", "")));
    }
}
