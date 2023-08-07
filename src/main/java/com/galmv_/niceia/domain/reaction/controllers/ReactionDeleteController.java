package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.domain.reaction.services.ReactionDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/reaction/{id}")
@RequiredArgsConstructor
@Slf4j
public class ReactionDeleteController {

    private final ReactionDeleteService reactionDeleteService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.reactionDeleteService.execute(id);

            return ResponseEntity.noContent().build();
        }
        catch (ReactionNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
