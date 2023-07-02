package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.TestFactory;

import com.galmv_.niceia.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestUpdateCommentService extends TestFactory {

    @Test
    @DisplayName("should to be possible to update a comment if this one exists")
    public void testSuccessUpdate(){
        this.commentService.update(comment.getId(), "new comment");

        String textToTestEquals = this.commentService.findById(comment.getId()).getText();

        Assert.assertEquals("new comment", textToTestEquals);
    }

    @Test
    @DisplayName("should not to be possible update a comment if this one not exists")
    public void testFailUpdate(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.commentService.update(new UUID(0, 0), "new comment"));
    }
}
