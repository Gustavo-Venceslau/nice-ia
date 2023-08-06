package com.galmv_.niceia.domain.comment.controllers;

import com.galmv_.niceia.domain.comment.exceptions.CommentNotFoundException;
import com.galmv_.niceia.domain.comment.services.CommentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/comment/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentDeleteController {

    private final CommentDeleteService commentDeleteService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.commentDeleteService.execute(id);

            return ResponseEntity.noContent().build();
        }
        catch (CommentNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
