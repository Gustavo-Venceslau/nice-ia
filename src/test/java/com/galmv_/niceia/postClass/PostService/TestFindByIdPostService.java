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
public class TestFindByIdPostService {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private PostRepository postRepository;

    @Autowired
    private PostService service;

    Student student4 = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);

    Post post1 = new Post(null, "Good day", "", student4);


    @Test
    @DisplayName("it should to be able to find a post by id")
    public void testSuccessFindById(){
        studentRepository.save(student4);
        postRepository.save(post1);

        Post post = this.service.findById(post1.getId());

        Assert.assertNotNull(post);
    }

    @Test
    @DisplayName("it should not to be able to find a post by id whether don't exists")
    public void testFailFindById(){
        postRepository.deleteAll();
        Assert.assertThrows(PostNotFoundedException.class, () -> this.service.findById(new UUID(0, 0)));
    }

}
