package com.galmv_.niceia.testFactories;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.testFactories.data.MakeComment;
import com.galmv_.niceia.testFactories.data.MakePost;
import com.galmv_.niceia.testFactories.data.MakeReaction;
import com.galmv_.niceia.testFactories.data.MakeStudent;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    protected Student student = MakeStudent.execute();
    protected Post post = MakePost.execute(student);
    protected Comment comment = MakeComment.execute(post, student);
    protected Reaction reaction = MakeReaction.execute(post, comment, student);

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
