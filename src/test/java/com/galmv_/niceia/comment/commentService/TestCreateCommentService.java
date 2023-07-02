package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.comment.Comment;
import com.galmv_.niceia.comment.CommentDTO;
import com.galmv_.niceia.comment.exceptions.CommentIsEmptyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestCreateCommentService extends TestFactory {

    @Test
    @DisplayName("should to be able to create a comment")
    public void testSuccessCreate(){
        CommentDTO commentDTO = new CommentDTO("Good Evening", post, student);

        Comment comment1 = this.commentService.create(commentDTO);

        Assert.assertNotNull(commentRepository.findById(comment1.getId()));
    }

    @Test
    @DisplayName("should not to be possible to create a comment if comment text is empty")
    public void testFailCreate(){
        CommentDTO commentDTO = new CommentDTO("", post, student);

        Assert.assertThrows(CommentIsEmptyException.class, () -> this.commentService.create(commentDTO));
    }

}
