package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.UUID;

public class TestFindAllByStudentCommentService extends UnitTestFactory {

    @Test
    @DisplayName("should to be possible to find all comments")
    public void testSuccessFindAllByStudent(){
        List<Comment> commentsList = this.commentService.findAllByStudent(student.getId());

        Assert.assertFalse(commentsList.isEmpty());
    }

    @Test
    @DisplayName("must not be able to find All student comments if the student does not exist")
    public void testFailsWithInvalidStudent(){
        Assert.assertThrows(UserNotFoundException.class, () ->
                this.commentService.findAllByStudent(new UUID(0, 0)));
    }

}