package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.services.FindAllByCommentReactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reaction/comment/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindAllByCommentReactionController {

    private final FindAllByCommentReactionService findAllByCommentReactionService;

    @GetMapping
    public ResponseEntity<List<Reaction>> handle(@PathVariable("id") UUID id){
        try {
            List<Reaction> reactions = this.findAllByCommentReactionService.execute(id);

            return ResponseEntity.ok().body(reactions);
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
