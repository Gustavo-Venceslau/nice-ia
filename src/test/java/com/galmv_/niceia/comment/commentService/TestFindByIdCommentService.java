package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.domain.comment.services.FindByIdCommentService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestFindByIdCommentService extends UnitTestFactory {

    @Autowired
    private FindByIdCommentService findByIdCommentService;

    @Test
    @DisplayName("should to be possible to find a comment by id if exists!")
    public void testSuccessFindById(){
        Comment commentToTest = this.findByIdCommentService.execute(comment.getId());

        Assert.assertNotNull(commentToTest);
    }

    @Test
    @DisplayName("should not to be possible find a comment by id if they don't exists")
    public void testFailFindById(){
        Assert.assertThrows(CommentNotFoundException.class, () -> this.findByIdCommentService.execute(new UUID(0, 0)));
    }

}
