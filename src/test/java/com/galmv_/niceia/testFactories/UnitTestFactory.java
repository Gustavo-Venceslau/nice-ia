package com.galmv_.niceia.testFactories;

import com.galmv_.niceia.auth.RegisterRequest;
import com.galmv_.niceia.auth.services.RegisterService;
import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
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
import jakarta.annotation.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Configuration
@RunWith(SpringRunner.class)
public class UnitTestFactory {

    @Resource
    protected StudentRepository studentRepository;
    @Resource
    protected PostRepository postRepository;
    @Resource
    protected CommentRepository commentRepository;
    @Resource
    protected ReactionRepository reactionRepository;
    @Autowired
    protected RegisterService registerService;

    protected Student student = MakeStudent.execute();
    protected RegisterRequest studentAuthenticate = new RegisterRequest("Gustavo", "de Almeida", "gustavo1@mail.com", "1231");
    protected Post post = MakePost.execute(student);
    protected Comment comment = MakeComment.execute(post, student);
    protected Reaction reaction = MakeReaction.execute(post, comment, student);

    @Before
    public void setUp(){
        studentRepository.save(student);
        registerService.execute(studentAuthenticate);
        postRepository.save(post);
        commentRepository.save(comment);
        reactionRepository.save(reaction);
    }

    @After
    public void setUpAfter(){
        reactionRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
        studentRepository.deleteAll();
    }
}
