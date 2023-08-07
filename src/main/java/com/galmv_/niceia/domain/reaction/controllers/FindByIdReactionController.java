package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.domain.reaction.services.FindByIdReactionService;
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
@RequestMapping("/reaction/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindByIdReactionController {

    private final FindByIdReactionService findByIdReactionService;

    @GetMapping
    public ResponseEntity<Reaction> handle(@PathVariable("id") UUID id){
        try {
            Reaction reaction = this.findByIdReactionService.execute(id);

            return ResponseEntity.ok().body(reaction);
        }
        catch (ReactionNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
