package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.comment.Comment;
import com.galmv_.niceia.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestDeleteCommentService extends TestFactory {

    @Test
    @DisplayName("should to be able to delete a comment if this one exists")
    public void testSuccessDelete(){
        Comment commentToDelete = this.commentRepository.save(new Comment(null, "to delete", post, student));

        this.commentService.delete(commentToDelete.getId());

        Assert.assertTrue(commentRepository.findById(commentToDelete.getId()).isEmpty());
    }

    @Test
    @DisplayName("should not to be able to delete a comment if this one don't exists")
    public void testFailDelete(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.commentService.delete(new UUID(0, 0)));
    }
}
