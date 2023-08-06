package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.domain.comment.services.CommentDeleteService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestDeleteCommentService extends UnitTestFactory {

    @Autowired
    private CommentDeleteService commentDeleteService;

    @Test
    @DisplayName("should to be able to delete a comment if this one exists")
    public void testSuccessDelete(){
        Comment commentToDelete = this.commentRepository.save(new Comment(null, "to delete", post, student));

        this.commentDeleteService.execute(commentToDelete.getId());

        Assert.assertTrue(commentRepository.findById(commentToDelete.getId()).isEmpty());
    }

    @Test
    @DisplayName("should not to be able to delete a comment if this one don't exists")
    public void testFailDelete(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.commentDeleteService.execute(new UUID(0, 0)));
    }
}
