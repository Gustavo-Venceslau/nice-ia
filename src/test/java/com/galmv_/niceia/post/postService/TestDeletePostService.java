package com.galmv_.niceia.post.postService;

import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

public class TestDeletePostService extends UnitTestFactory {
    @Test
    @DisplayName("should to be able to delete a post if it exist")
    public void testSuccessDeletePost(){

        Student student2 = new Student(null, "Gus", "Almeida", "gustavodeal@gmail.com", "1234", StudentRole.USER);

        studentRepository.save(student2);

        Post post2 = new Post(null, "Good Afternoon", "Image", student2);

        postRepository.save(post2);

        this.postRepository.delete(post2);

        Assert.assertTrue(postRepository.findById(post2.getId()).isEmpty());
    }

    @Test
    @DisplayName("should not to be able to delete a post if it don't exists")
    public void testFailDeletePost(){
        Assert.assertThrows(PostNotFoundedException.class, () -> this.postService.delete(new UUID(0, 0)));
    }
}
