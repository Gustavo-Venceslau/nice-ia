package com.galmv_.niceia.comment.commentService;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import com.galmv_.niceia.shared.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestCreateCommentService extends UnitTestFactory {

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("should to be able to create a comment")
    public void testSuccessCreate(){
        String token = "Bearer " + jwtService.generateToken(student);

        Comment comment1 = this.commentService.create("Good Evening", post.getId(), token);

        Assert.assertNotNull(commentRepository.findById(comment1.getId()));
    }

    @Test
    @DisplayName("should not to be possible to create a comment if comment text is empty")
    public void testFailCreate(){
        String token = "Bearer " + jwtService.generateToken(student);

        Assert.assertThrows(EntityNotFoundException.class, () ->
                this.commentService.create("", post.getId(), token));
    }

    @Test
    @DisplayName("should not to be possible to create a comment if post don't exist")
    public void testFailCreateWithUnexistingPost(){
        String token = "Bearer " + jwtService.generateToken(student);

        Assert.assertThrows(EntityNotFoundException.class, () ->
                this.commentService.create("", new UUID(0, 0), token));
    }

    @Test
    @DisplayName("should not to be possible to create a comment if token is not valid")
    public void testFailCreateWithInvalidToken(){
        String token = "Bearer " + jwtService.generateToken(
                new Student(null, "bella", "bella", "bella@mail.com", "123", StudentRole.USER));

        Assert.assertThrows(EntityNotFoundException.class, () ->
                this.commentService.create("", post.getId(), token));
    }
}
