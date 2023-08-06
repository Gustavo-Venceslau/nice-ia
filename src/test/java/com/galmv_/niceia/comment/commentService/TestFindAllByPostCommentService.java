package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.services.FindAllByPostCommentService;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TestFindAllByPostCommentService extends UnitTestFactory {

    @Autowired
    private FindAllByPostCommentService findAllByPostCommentService;

    @Test
    @DisplayName("should to be able to find all comments by post")
    public void testSuccessFindAllByPost(){
        List<Comment> comments = this.findAllByPostCommentService.execute(post.getId());

        Assert.assertFalse(comments.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find all comments by post if this one not exists")
    public void testFailFindAllByPost(){

        Assert.assertThrows(PostNotFoundedException.class, () ->
                this.findAllByPostCommentService.execute(new UUID(0, 0)));
    }
}
