package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.comment.Comment;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class TestFindAllByStudentCommentService extends TestFactory {

    @Test
    @DisplayName("should to be possible to find all comments")
    public void testSuccessFindAllByStudent(){
        List<Comment> commentsList = this.commentService.findAllByStudent(student.getEmail());

        Assert.assertFalse(commentsList.isEmpty());

    }

    @Test
    @DisplayName("must not be able to find All student comments if the student does not exist")
    public void testFailsWithInvalidStudent(){
        Assert.assertThrows(UserNotFoundException.class, () -> this.commentService.findAllByStudent(null));
    }

}