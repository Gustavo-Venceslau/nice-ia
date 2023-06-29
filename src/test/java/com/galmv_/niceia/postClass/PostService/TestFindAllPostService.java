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

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFindAllPostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private StudentRepository studentRepository;

    @Autowired
    private PostService service;

    @Test
    @DisplayName("it should be able to find all posts")
    public void testSuccessFindAll(){
        Student student4 = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);
        Student student5 = new Student(null, "Venceslau", "Almeida", "venceslau@mail.com", "123456", StudentRole.USER);

        this.studentRepository.saveAll(List.of(student4, student5));

        Post post1 = new Post(null, "Good day", "", student4);
        Post post2 = new Post(null, "Good Evening", "", student5);
        Post post3 = new Post(null, "Good Afternoon", "", student4);

        this.postRepository.saveAll(List.of(post1, post2, post3));

        List<Post> posts = this.service.findAll();

        Assert.assertFalse(posts.isEmpty());
    }

    @Test
    @DisplayName("it should not to be able find all whether a list is empty")
    public void testFailFindAll(){
        this.postRepository.deleteAll();

        Assert.assertThrows(PostNotFoundedException.class, () -> this.service.findAll());
    }
}
