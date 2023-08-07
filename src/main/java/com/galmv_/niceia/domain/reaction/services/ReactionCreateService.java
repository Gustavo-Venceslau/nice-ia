package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.comment.CommentRepository;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.post.PostRepository;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionDTO;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionCreateService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final StudentRepository studentRepository;
    private final ReactionRepository repository;

    public Reaction execute(ReactionDTO reactionDTO) {
        Optional<Post> optionalReaction = this.postRepository.findById(reactionDTO.post());
        Optional<Comment> optionalComment = this.commentRepository.findById(reactionDTO.comment());
        Optional<Student> optionalStudent = this.studentRepository.findById(reactionDTO.student());

        if(optionalReaction.isEmpty() || optionalComment.isEmpty() || optionalStudent.isEmpty()){
            throw new PostNotFoundedException("Post Not Founded");
        }

        Reaction reaction = new Reaction(null, reactionDTO.type(), optionalReaction.get(), optionalComment.get(), optionalStudent.get());

        return this.repository.save(reaction);
    }
}
