package com.galmv_.niceia;

import com.galmv_.niceia.auth.AuthenticationRequest;
import com.galmv_.niceia.auth.AuthenticationService;
import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.comment.Comment;
import com.galmv_.niceia.comment.CommentRepository;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.reaction.Enums.ComponentType;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.reaction.Reaction;
import com.galmv_.niceia.reaction.ReactionRepository;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.StudentRepository;
import com.galmv_.niceia.student.enums.StudentRole;
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
    private WebApplicationContext wac;

    @Autowired
    protected JwtService jwtService;

    protected MockMvc mockMvc;

    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected CommentRepository commentRepository;
    @Autowired
    protected ReactionRepository reactionRepository;

    @Autowired
    protected AuthenticationService authenticationService;

    protected Student student = new Student(null, "Gustavo", "de Almeida", "gustavodealmeida@gmail.com", "1234", StudentRole.USER);
    protected RegisterRequest studentAuth = new RegisterRequest("venceslau", "ven","gustavovences@mail.com", "321");
    protected Post post = new Post(null, "Good Morning", "ImageURL", student);
    protected Comment comment = new Comment(null, "Good way, guys!", post, student);
    protected Reaction reaction = new Reaction(null, Type.LIKE, ComponentType.POST, post.getId(), student);

    @Before
    public void setUp(){
        studentRepository.save(student);
        postRepository.save(post);
        commentRepository.save(comment);
        reactionRepository.save(reaction);

        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void setUpAfter(){
        reactionRepository.delete(reaction);
        commentRepository.delete(comment);
        postRepository.delete(post);
        studentRepository.delete(student);

        Mockito.reset();
    }
}
