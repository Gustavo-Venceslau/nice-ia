package com.galmv_.niceia.postClass.PostService;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.post.PostService;
import com.galmv_.niceia.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.enums.StudentRole;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDeletePostService {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private PostRepository postRepository;

    @Autowired
    private PostService service;

    Student student = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);

    Post post = new Post(null, "Good day", "", student);

    @Test
    @DisplayName("should to be able to delete a post if it exist")
    public void testSuccessDeletePost(){
        this.studentRepository.save(student);
        this.service.create(post);

        this.service.delete(post.getId());

        Assert.assertTrue(postRepository.findById(post.getId()).isEmpty());
    }

    @Test
    @DisplayName("should not to be able to delete a post if it don't exists")
    public void testFailDeletePost(){
        postRepository.deleteAll();
        Assert.assertThrows(PostNotFoundedException.class, () -> this.service.delete(new UUID(0, 0)));
    }
}
