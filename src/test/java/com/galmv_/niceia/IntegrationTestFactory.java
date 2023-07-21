package com.galmv_.niceia;

import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTestFactory {
    @Autowired
    protected JwtService jwtService;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected CommentRepository commentRepository;
    @Autowired
    protected ReactionRepository reactionRepository;

    protected Student student = new Student(null, "Gustavo", "de Almeida", "gustavodealmeida@gmail.com", "1234", StudentRole.USER);
    protected Post post = new Post(null, "Good Morning", "ImageURL", student);
    protected Comment comment = new Comment(null, "Good way, guys!", post, student);
    protected Reaction reaction = new Reaction(null, Type.LIKE, post, comment, student);

    @Before
    public void setUp(){
        studentRepository.save(student);
        postRepository.save(post);
        commentRepository.save(comment);
        reactionRepository.save(reaction);
    }

    @After
    public void setUpAfter(){
        reactionRepository.delete(reaction);
        commentRepository.delete(comment);
        postRepository.delete(post);
        studentRepository.delete(student);
    }
}
