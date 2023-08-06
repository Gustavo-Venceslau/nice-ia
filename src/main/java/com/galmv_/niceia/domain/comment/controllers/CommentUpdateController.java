package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.dtos.CreateAndUpdateCommentDTO;
import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.comment.services.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentUpdateController {

    private final CommentUpdateService commentUpdateService;

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody CreateAndUpdateCommentDTO newData){
        try {
            this.commentUpdateService.execute(id, newData);

            return ResponseEntity.noContent().build();
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
