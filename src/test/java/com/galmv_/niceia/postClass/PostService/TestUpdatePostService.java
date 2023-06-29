package com.galmv_.niceia.postClass.PostService;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostDTO;
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

import java.util.Objects;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUpdatePostService {

    @Resource
    private StudentRepository studentRepository;

    @Autowired
    private PostService service;

    Student student = new Student(null, "Almeida", "Venceslau", "almeida@mail.com", "12345", StudentRole.USER);

    Post post = new Post(null, "Good day", "", student);

    @Test
    @DisplayName("it should to be able to update a post if exist")
    public void testSuccessUpdatePost(){
        this.studentRepository.save(student);
        this.service.create(post);

        PostDTO postDataToUpdate = new PostDTO("title", "image2");

        this.service.update(post.getId(), postDataToUpdate);

        Assert.assertNotEquals("Good day", service.findById(post.getId()).getTitle());
    }

    @Test
    @DisplayName("it should not to be able to update a post that not exists")
    public void testFailUpdatePost(){
        Assert.assertThrows(PostNotFoundedException.class, ()
                -> this.service.update(new UUID(0, 0), new PostDTO("", "")));
    }

}
