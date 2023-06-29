package com.galmv_.niceia.postClass.PostService;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.post.PostService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCreatePostService {

    @Resource
    private StudentRepository studentRepository;

    @Autowired
    private PostService service;

    Student student = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);

    Post post = new Post(null, "Good day", "", student);

    @Test
    @DisplayName("it should to be able to create a post")
    public void testSuccessCreatePost(){
        this.studentRepository.save(student);
        this.service.create(post);

        Assert.assertNotNull(post.getId());
    }
}
