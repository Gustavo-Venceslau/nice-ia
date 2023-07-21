package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestFindByIdCommentService extends UnitTestFactory {

    @Test
    @DisplayName("should to be possible to find a comment by id if exists!")
    public void testSuccessFindById(){
        Comment commentToTest = this.commentService.findById(comment.getId());

        Assert.assertNotNull(commentToTest);
    }

    @Test
    @DisplayName("should not to be possible find a comment by id if they don't exists")
    public void testFailFindById(){
        Assert.assertThrows(CommentNotFoundException.class, () -> this.commentService.findById(new UUID(0, 0)));
    }

}
