package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.domain.post.services.FindAllByStudentPostService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TestFindAllByStudentPostService extends UnitTestFactory {

    @Autowired
    private FindAllByStudentPostService findAllByStudentPostService;

    @Test
    @DisplayName("should to be able to find all posts by student")
    public void testSuccessFindAllByStudent(){
        List<Post> postsByStudent = this.findAllByStudentPostService.execute(student.getId());

        Assert.assertFalse(postsByStudent.isEmpty());
    }

    @Test
    @DisplayName("should not to be able to find all posts by student if this one don't exists")
    public void testFailFindAllByStudent(){
        Assert.assertThrows(UserNotFoundException.class, () -> this.findAllByStudentPostService.execute(new UUID(0, 0)));
    }
}
