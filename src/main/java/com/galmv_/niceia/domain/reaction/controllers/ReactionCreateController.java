package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionDTO;
import com.galmv_.niceia.domain.reaction.services.ReactionCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reaction")
@RequiredArgsConstructor
@Slf4j
public class ReactionCreateController {

    private final ReactionCreateService reactionCreateService;

    @PostMapping
    public ResponseEntity<Reaction> create(@RequestBody ReactionDTO reactionDTO){
        try {
            Reaction reaction = this.reactionCreateService.execute(reactionDTO);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reaction.getId()).toUri();

            return ResponseEntity.created(uri).body(reaction);
        }
        catch (RuntimeException e){
            log.info("Exception: " + e);

            return ResponseEntity.badRequest().build();
        }
    }
}
