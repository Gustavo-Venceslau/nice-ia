package com.galmv_.niceia.domain.reaction;

import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.post.exceptions.PostNotFoundedException;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reaction")
@RequiredArgsConstructor
@Slf4j
public class ReactionController {

    private final ReactionService service;

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<List<Reaction>> findAllByStudent(@PathVariable("id") UUID id){
        try {
            List<Reaction> reactionsByStudent = this.service.findAllByStudent(id);

            return ResponseEntity.ok().body(reactionsByStudent);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<Reaction>> findAllByPost(@PathVariable("id") UUID id){
        try {
            List<Reaction> reactions = this.service.findAllByPost(id);

            return ResponseEntity.ok().body(reactions);
        }
        catch (PostNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/comment/{id}")
    public ResponseEntity<List<Reaction>> findAllByComment(@PathVariable("id") UUID id){
        try {
            List<Reaction> reactions = this.service.findAllByComment(id);

            return ResponseEntity.ok().body(reactions);
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reaction> create(@RequestBody ReactionDTO reactionDTO){
        try {
            Reaction reaction = this.service.create(reactionDTO);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reaction.getId()).toUri();

            return ResponseEntity.created(uri).body(reaction);
        }
        catch (RuntimeException e){
            log.info("Exception: " + e);

            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Type type){
        try {
            this.service.update(id, type);

            return ResponseEntity.noContent().build();
        }
        catch (ReactionNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.service.delete(id);

            return ResponseEntity.noContent().build();
        }
        catch (ReactionNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
