package com.galmv_.niceia.reaction;

import com.galmv_.niceia.comment.CommentRepository;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.reaction.Enums.ComponentType;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.reaction.exceptions.ComponentReactionNotFoundedException;
import com.galmv_.niceia.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

    private final StudentService studentService;

    public Reaction findById(UUID id) {
        Optional<Reaction> optionalReaction = this.reactionRepository.findById(id);

        if(optionalReaction.isEmpty()){
            throw new ReactionNotFoundedException("Reaction not founded");
        }

        return optionalReaction.get();
    }

    public List<Reaction> findAllByStudent(UUID id) {
        Student student = studentService.findById(id);

        return reactionRepository.findAllByStudent(student);
    }

    public Reaction create(ReactionDTO reactionDTO) {
        if(reactionDTO.componentType() == ComponentType.POST){
            if (postRepository.findById(reactionDTO.componentId()).isEmpty()){
                throw new ComponentReactionNotFoundedException("Reaction post not founded");
            }
        }
        else{
            if (commentRepository.findById(reactionDTO.componentId()).isEmpty()){
                throw new ComponentReactionNotFoundedException("Reaction comment not founded");
            }
        }

        Reaction reaction = new Reaction(null, reactionDTO.type(), reactionDTO.componentType(), reactionDTO.componentId(), reactionDTO.student());

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
