package com.galmv_.niceia.domain.reaction.controllers;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.services.FindAllByStudentReactionService;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
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
@RequestMapping("/reaction/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindAllByStudentReactionController {

    private final FindAllByStudentReactionService findAllByStudentReactionService;

    @GetMapping
    public ResponseEntity<List<Reaction>> handle(@PathVariable("id") UUID id){
        try {
            List<Reaction> reactionsByStudent = this.findAllByStudentReactionService.execute(id);

            return ResponseEntity.ok().body(reactionsByStudent);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
