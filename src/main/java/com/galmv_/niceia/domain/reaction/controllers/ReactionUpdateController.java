package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.exceptions.ReactionNotFoundedException;
import com.galmv_.niceia.domain.reaction.services.ReactionUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reaction/{id}")
@RequiredArgsConstructor
@Slf4j
public class ReactionUpdateController {

    private final ReactionUpdateService reactionUpdateService;

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody Type type){
        try {
            this.reactionUpdateService.execute(id, type);

            return ResponseEntity.noContent().build();
        }
        catch (ReactionNotFoundedException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
