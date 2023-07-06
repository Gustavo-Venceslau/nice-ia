package com.galmv_.niceia;

import com.galmv_.niceia.comment.Comment;
import com.galmv_.niceia.comment.CommentService;
import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostService;
import com.galmv_.niceia.reaction.Enums.ComponentType;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.reaction.Reaction;
import com.galmv_.niceia.reaction.ReactionService;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.enums.StudentRole;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTestFactory {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected StudentRepository studentRepository;

    protected Student student = new Student(null, "Gustavo", "de Almeida", "gustavodealmeida@gmail.com", "1234", StudentRole.USER);

    @Before
    public void setUp(){
        this.studentRepository.save(student);
    }
}
