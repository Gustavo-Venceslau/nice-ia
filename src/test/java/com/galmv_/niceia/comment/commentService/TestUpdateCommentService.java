package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.domain.comment.services.CommentUpdateService;
import com.galmv_.niceia.domain.comment.services.FindByIdCommentService;
import com.galmv_.niceia.testFactories.UnitTestFactory;

import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestUpdateCommentService extends UnitTestFactory {

    @Autowired
    private CommentUpdateService commentUpdateService;
    @Autowired
    private FindByIdCommentService findByIdCommentService;

    CreateAndUpdateCommentDTO newData = new CreateAndUpdateCommentDTO("new comment");

    @Test
    @DisplayName("should to be possible to update a comment if this one exists")
    public void testSuccessUpdate(){

        this.commentUpdateService.execute(comment.getId(), newData);

        String textToTestEquals = this.findByIdCommentService.execute(comment.getId()).getText();

        Assert.assertEquals("new comment", textToTestEquals);
    }

    @Test
    @DisplayName("should not to be possible update a comment if this one not exists")
    public void testFailUpdate(){
        Assert.assertThrows(CommentNotFoundException.class, () ->
                this.commentUpdateService.execute(new UUID(0, 0), newData));
    }
}
