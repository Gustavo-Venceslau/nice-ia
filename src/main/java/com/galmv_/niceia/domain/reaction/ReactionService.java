package com.galmv_.niceia.domain.reaction;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.comment.services.FindByIdCommentService;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.post.services.FindByIdPostService;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.services.FindByIdStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final StudentRepository studentRepository;

    private final FindByIdPostService findByIdPostService;

    private final FindByIdCommentService findByIdCommentService;

    private final FindByIdStudentService findByIdStudentService;

    public Reaction findById(UUID id) {
        Optional<Reaction> optionalReaction = this.reactionRepository.findById(id);

        if(optionalReaction.isEmpty()){
            throw new ReactionNotFoundedException("Reaction not founded");
        }

        return optionalReaction.get();
    }

    public List<Reaction> findAllByStudent(UUID id) {
        Student student = findByIdStudentService.execute(id);

        return reactionRepository.findAllByStudent(student);
    }

    public List<Reaction> findAllByPost(UUID id){
        Post post = this.findByIdPostService.execute(id);

        return this.reactionRepository.findAllByPost(post);
    }

    public List<Reaction> findAllByComment(UUID id) {
        Comment commentToFind = this.findByIdCommentService.execute(id);

        return this.reactionRepository.findAllByComment(commentToFind);
    }

    public Reaction create(ReactionDTO reactionDTO) {
        Optional<Post> optionalReaction = this.postRepository.findById(reactionDTO.post());
        Optional<Comment> optionalComment = this.commentRepository.findById(reactionDTO.comment());
        Optional<Student> optionalStudent = this.studentRepository.findById(reactionDTO.student());

        if(optionalReaction.isEmpty() || optionalComment.isEmpty() || optionalStudent.isEmpty()){
            throw new PostNotFoundedException("Post Not Founded");
        }

        Reaction reaction = new Reaction(null, reactionDTO.type(), optionalReaction.get(), optionalComment.get(), optionalStudent.get());

        return this.reactionRepository.save(reaction);
    }

    public void update(UUID id, Type type) {
        Reaction reaction = findById(id);

        reaction.setType(type);

        reactionRepository.save(reaction);
    }

    public void delete(UUID id) {
        Reaction reaction = findById(id);

        this.reactionRepository.delete(reaction);
    }
}
