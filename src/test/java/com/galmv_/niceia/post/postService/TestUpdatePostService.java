package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.domain.post.services.FindByIdPostService;
import com.galmv_.niceia.domain.post.services.PostUpdateService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.PostDTO;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestUpdatePostService extends UnitTestFactory {

    @Autowired
    private PostUpdateService postUpdateService;

    @Autowired
    private FindByIdPostService findByIdPostService;

    @Test
    @DisplayName("it should to be able to update a post if exist")
    public void testSuccessUpdatePost(){

        PostDTO postDataToUpdate = new PostDTO("title", "image2", student.getId());

        this.postUpdateService.execute(post.getId(), postDataToUpdate);

        Assert.assertNotEquals("Good day", findByIdPostService.execute(post.getId()).getTitle());
    }

    @Test
    @DisplayName("it should not to be able to update a post that not exists")
    public void testFailUpdatePost(){
        Assert.assertThrows(PostNotFoundedException.class, ()
                -> this.postUpdateService.execute(new UUID(0, 0), new PostDTO("", "", student.getId())));
    }
}
