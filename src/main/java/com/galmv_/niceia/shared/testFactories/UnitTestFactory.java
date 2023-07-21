package com.galmv_.niceia.shared.testFactories;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.comment.CommentService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.PostService;
import com.galmv_.niceia.domain.reaction.Enums.ComponentType;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.reaction.ReactionService;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.StudentService;
import com.galmv_.niceia.domain.student.enums.StudentRole;
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
    protected StudentService studentService;
    @Autowired
    protected PostService postService;
    @Autowired
    protected CommentService commentService;
    @Autowired
    protected ReactionService reactionService;

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
        reactionRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
        studentRepository.deleteAll();
    }
}
