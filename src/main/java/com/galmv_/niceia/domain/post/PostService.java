package com.galmv_.niceia.domain.post;

import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.config.JwtService;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    private final StudentRepository studentRepository;

    private final CommentRepository commentRepository;

    private final ReactionRepository reactionRepository;

    private final JwtService jwtService;

    public List<Post> findAll() {
        List<Post> posts = this.repository.findAll();

        if(posts.isEmpty()){
            throw new PostNotFoundedException("Posts not founded!");
        }

        return posts;
    }

    public Post findById(UUID id){
        Optional<Post> post = this.repository.findById(id);

        if(post.isEmpty()){
            throw new PostNotFoundedException("Post not founded");
        }

        return post.get();
    }

    public List<Post> findAllByStudent(UUID id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        return repository.findAllByStudent(optionalStudent.get());
    }

    public Post create(PostDTO post, String token){
        Student student = findUserToCretePost(token);

        Post postToSave = Post.builder()
                .title(post.title())
                .imageURL(post.imageURL())
                .student(student)
                .build();

        return this.repository.save(postToSave);
    }
    private Student findUserToCretePost(String token){
        System.out.println(token);

        String jwtTokenFormatted = token.split(" ")[1].trim();

        String username = jwtService.extractUsername(jwtTokenFormatted);
        Optional<Student> optionalStudent = studentRepository.findByEmail(username);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not found to create a post");
        }

        return optionalStudent.get();
    }

    public void update(UUID id, PostDTO postNewData) {
        Post postToUpdate = findById(id);

        updatePostData(postToUpdate, postNewData);

        this.repository.save(postToUpdate);
    }

    private void updatePostData(Post postToUpdate, PostDTO postNewData) {
        postToUpdate.setTitle(postNewData.title());
        postToUpdate.setImageURL(postNewData.imageURL());
    }

    @Transactional
    public void delete(UUID id) {
        Post postToDelete = findById(id);

        if(postToDelete == null){
            throw new PostNotFoundedException("post not founded");
        }

        this.reactionRepository.deleteAllByPost(postToDelete);
        this.commentRepository.deleteAllByPost(postToDelete);
        this.repository.delete(postToDelete);
    }
}
